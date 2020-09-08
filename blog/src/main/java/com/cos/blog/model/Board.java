package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				// getter, setter
@AllArgsConstructor // 모든 필드를 다 포함하는 생성자
@NoArgsConstructor	// 빈 생성자
@Builder			// 빌더 패턴
@Entity
public class Board {
	
	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) 프로젝트에서 연결된 DB 의 넘버링 전략을 따라간다.
	 * 결국, 오라클 => sequence 를 사용. / mysql => auto-increment 를 사용.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//--- 제목
	@Column(nullable = false, length = 100)
	private String title;
	
	//--- 내용 - 대용량 데이터(섬머노트 라이브러리 <html>태그가 섞여서 디자인이됨.)
	@Lob
	@Column(nullable = false)
	private String content;
	
	//--- 조회수
	@ColumnDefault("0")
	private int count;
	
	//--- @CreationTimestamp : 시간이 자동으로 입력된다.
	@CreationTimestamp
	private Timestamp createDate;
	
	/*
	 * ORM 에서는 userId 와 같은 FK 값으로 찾지 않고, BlogUser Object 를 바로 넣는다.
	 * DB 에서는 Object 를 저장할 수 없다. 그래서 FK 를 사용하는데, 객체지향 프로그래밍에서는
	 * Object 를 저장할 수 있다.
	 * 
	 * @ManyToOne => Many : Board, BlogUser : One = 한 명의 유저는 여러개의 글을 쓸 수 있다. 
	 * @ManyToOne 는 Board 테이블 조회 시 BlogUser 정보는 가져올 데이터가 한건 밖에 되지 않으므로 가져오겠다.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private BlogUser user;
	
	/*
	 * mappedBy : 연관 관계의 주인이 아니다.(FK 가 아님)
	 * 그러므로, DB 에 컬럼을 만들지 않아야 한다.
	 * Board 를 select 할 때 Join 문을 통해 값을 얻기 위해 필요할 뿐이다.
	 * mappedBy = "board" = Reply 클래스의 board 필드명이다.
	 */
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Reply> reply;

}
