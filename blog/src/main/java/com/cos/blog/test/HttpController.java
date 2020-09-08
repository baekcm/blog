package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controller 의 역할 :
 * 사용자가 요쳥 => 응답(Data) 		: @RestController
 * 사용자가 요쳥 => 응답(HTML 파일) 	: @Controller
 */
@RestController
public class HttpController {
	
	private static final String TAG = "HttpControllerTest";
	
	//--- get 방식이므로 브라우저에서 바로 확인 가능하다.
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member member = new Member(1, "ssar", "1111", "email");
		System.out.println(TAG + "getter : " + member.getId());
		member.setId(5000);
		System.out.println(TAG + "setter : " + member.getId());
		return "lombok test 완료";
	}
	
	//--- get 방식이므로 브라우저에서 바로 확인 가능하다.
	@GetMapping("/http/lombokBuilder")
	public String lombokBuilderTest() {
		Member2 member = Member2.builder().username("ssar").password("2222").email("baekcm@gmail.com").build();
		System.out.println(TAG + "getter : " + member.getUsername());
		member.setUsername("cos");;
		System.out.println(TAG + "setter : " + member.getUsername());
		return "lombokBuilder test 완료";
	}
	
	//--- get(select), post(insert), put(update), delete(delete)
	//--- 인터넷 브라우저 요청은 무조건  get 요청밖에 할 수 없다.
	//--- postman 사용하여 get, post, put, delete 요청 시 에러 없이 응답 받을 수 있다.
	
	//--- 주소 : http://localhost:8080/http/get (select)
	/*
	@GetMapping("/http/get")
	public String getTest(@RequestParam int id, @RequestParam String username) {
		return "get 요청 : " + id + ", " + username;
	}
	*/
	//--- postman 을 통해 http://localhost:8080/http/get?id=1&username=ssar&password=1234 queryString 값을
	//--- get 방식으로 호출하면 "?" 뒤의 값에 대해 Member 객체에 자동으로 Mapping 시켜준다.
	//--- get 방식으로 데이터를 요청할 때 어떤 데이터를 요청할건지 요청하는 방법은 queryString 밖에 없다.
	@GetMapping("/http/get")
	public String getTest(Member member) {
		return "get 요청 : " + member.getId() + ", " + member.getUsername() + ", " + member.getPassword();
	}
	
	//--- 주소 : http://localhost:8080/http/post (insert) => 405 에러 발생
	//--- postman 을 통해 http://localhost:8080/http/post
	//--- Body 에 key(id, username, password, email) / value(1, ssar, 1234, baekcm@gmail.com)
	/*
	@PostMapping("/http/post")
	public String postTest(Member member) {
		return "post 요청 : " + member.getId() + ", " + member.getUsername() + ", " + member.getPassword() + ", " + member.getEmail();
	}
	*/
	//--- postman 을 통해 http://localhost:8080/http/post
	//--- raw type 을 Text(text/plain) 인 경우 postman 에서 json 형식으로 보내도 그대로 받아서 출력된다.
	/*
	 * 	{
			"id" : 1,
			"username" : "baekcm",
			"password" : 12345,
			"email" : "baekcm@gmail.com"
		}
	@PostMapping("/http/post")
	public String postTest(@RequestBody String text) {
		return "post 요청 : " + text;
	}
	*/
	//--- postman 을 통해 http://localhost:8080/http/post
	//--- raw type 을 JSON(application/json) 인 경우 postman 에서 json 형식으로 보내면 get 처럼 받을 수 있다.	
	//--- 이러한 자동 Mapping 처리를 MessageConverter(SpringBoot) 에서 한다.
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member member) {
		return "post 요청 : " + member.getId() + ", " + member.getUsername() + ", " + member.getPassword() + ", " + member.getEmail();
	}
	
	
	//--- 주소 : http://localhost:8080/http/put (update) => 405 에러 발생
	/*
	 * 	{
			"password" : 1234567,
			"email" : "baeckwind@naver.com"
		}
	 */
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member member) {
		return "put 요청 : " + member.getPassword() + ", " + member.getEmail();
	}
	
	//--- 주소 : http://localhost:8080/http/delete (delete) => 405 에러 발생
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
