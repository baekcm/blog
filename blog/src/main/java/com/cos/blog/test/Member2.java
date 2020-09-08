package com.cos.blog.test;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				// getter, setter
@NoArgsConstructor	// 빈 생성자
@Entity
public class Member2 {

	@Id
	private int id;
	private String username;
	private String password;
	private String email;

	//--- id 값은 자동으로 증가하는 sequence 역할을 하게 하고 싶은 경우 직접 값을 넣지 않아야 하므로 
	//--- id 를 제외한 별도의 생성자를 따로 만들지 않고, 사용할 수 있게 하는 방법이다.
	//--- HttpController.java 파일에서 사용 방법을 참조하자.
	@Builder
	public Member2(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
}
