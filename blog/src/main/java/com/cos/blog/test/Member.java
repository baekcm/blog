package com.cos.blog.test;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				// getter, setter
@AllArgsConstructor // 모든 필드를 다 포함하는 생성자
@NoArgsConstructor	// 빈 생성자
@Entity
public class Member {

	@Id
	private int id;
	private String username;
	private String password;
	private String email;
	
}
