package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) 프로젝트에서 연결된 DB 의 넘버링 전략을 따라간다.
	 * 결국, 오라클 => sequence 를 사용. / mysql => auto-increment 를 사용.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	//--- @ManyToOne : 여러개의 댓글은 하나의 게시글에 존재할 수 있다.
	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;
	
	//--- @ManyToOne : 여러개의 댓글을 한 명의 User 가 작성할 수 있다.
	@ManyToOne
	@JoinColumn(name="userId")
	private BlogUser user;
	
	//--- @CreationTimestamp : 시간이 자동으로 입력된다.
	@CreationTimestamp
	private Timestamp createDate;
	
}
