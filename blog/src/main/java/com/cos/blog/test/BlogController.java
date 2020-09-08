package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//--- 스프링이 com.cos.blog 패키지 이하를 모두 스캔해서 모든 파일을 메모리에 new 하는 것은 아니고,
//--- 특정 annotaion 이 붙어있는 class 파일들을 new 해서(Ioc) 스프링 컨테이너에 관리해준다.
@RestController
public class BlogController {

	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>Hello springBoot</h1>";
	}
	
}
