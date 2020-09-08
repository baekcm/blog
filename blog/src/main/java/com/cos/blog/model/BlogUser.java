package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//--- ORM : Java(다른 언어 포함) Object => 테이블로 매핑해주는 기술.
//--- @Entity : @Entity annotation 을 사용함으로써 User 클래스를 테이블화 시킨다.
//--- springboot 가 실행될 때 User 클래스를 읽어서 자동으로 테이블을 생성한다.
@Data				// getter, setter
@AllArgsConstructor // 모든 필드를 다 포함하는 생성자
@NoArgsConstructor	// 빈 생성자
@Builder			// 빌더 패턴
@Entity
public class BlogUser {
	
	/*
	 * oracle : sequence / mysql : auto-increment
	 * @Id annotation 을 사용함으로써 Primary key 로 설정한다.
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 * annotation 을 사용하여 넘버링 전략을 사용한다.
	 * 프로젝트에서 연결된 DB 의 넘버링 전략을 따라간다.
	 * 결국, 오라클 => sequence 를 사용. / mysql => auto-increment 를 사용.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//--- 아이디
	//--- null 값을 허용하지 않고, id 의 길이는 30자로 제한한다.
	@Column(nullable = false, length = 30)
	private String userId;
	
	//--- 비밀번호
	//--- 길이를 넉넉하게 하고, 추후 해쉬로 암호화하여 DB 에 넣을 것이다.
	@Column(nullable = false, length = 100)
	private String password;
	
	//--- 이메일
	@Column(nullable = false, length = 50)
	private String email;
	
	//--- Enum 을 쓰는게 좋다. Enum 을 쓰면 어떤 데이터에 도메인을 만들어줄 수 있다.(도메인 : 범위)
	//--- 만약, 어떤 사람이 회원가입 시 그 사람의 role 을 Admin or User or Manager 권한을 준다.
	//--- @ColumnDefault("'user'") 회원 가입 시 default 로 우선 user 권한을 준다.(홀타옴표 주의)
	@ColumnDefault("'user'")
	private String role;

	//--- 회원 가입 일시
	//--- @CreationTimestamp : 시간이 자동으로 입력된다.
	@CreationTimestamp
	private Timestamp createDate;
	
}
