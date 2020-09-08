package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * SpringBoot 는 기본적으로 jsp 파일을 지원하지 않는다.
 * 그래서 pom.xml 에 jsp template 엔진을(tomcat-jasper) 의존성 설정을 해줘야 한다.
 * 또한, 기본 경로가 src/main/resources/static 이기 때문에 해당 경로에 jsp 파일을 넣으면
 * 인식을 하지 못한다. 왜냐하면, static 은 spring 이 정적 파일들을 놓는 경로이기 때문이다.
 * 결론적으로, static 이하에는 브라우저가 인식할 수 있는 파일들만 놓아야 한다. 
 */

//--- @Controller annotation 사용 목적은 데이터가 아닌 파일을 return 하기 위함이다.
@Controller
public class TemplateController {

	//--- http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String templateHome() {
		System.out.println("templateHome()");
		//--- spring 은 기본적으로 파일을 return 한다고 할 때 Controller 라는 annotation 이 붙으면
		//--- 해당 메소드는 파일을 return 한다.
		//--- spring 파일 return 기본 경로 : src/main/resources/static
		//--- 리터명 : /home.html
		//--- 전체 경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	//--- image 정적 파일이 정상 출력되는지 테스트.
	@GetMapping("temp/img")
	public String templateImg() {
		return "/a.png";
	}
	
	//--- image 정적 파일이 정상 출력되는지 테스트.
	@GetMapping("temp/jsp")
	public String templateJsp() {
		return "home";
	}
	
}
