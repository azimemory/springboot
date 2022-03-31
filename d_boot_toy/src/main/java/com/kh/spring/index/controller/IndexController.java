package com.kh.spring.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	//1. @Controller : 해당 클래스를 applicationContext에 bean으로 등록
	//						Controller와 관련된 annotation을 사용할 수 있게 해준다.
	//2. @RequestMapping : 요청 URL과 Controller의 메서드 매핑을 지원
	//						클래스 위에 선언할 경우, 해당 클래스의 모든 매서드가 지정된 경로를 상위경로로 가진다.
	//3. @GetMapping : Get 방식의 요청 URL과 Controller의 메서드 매핑을 지원
	//4. @PostMapping : Post 방식의 요청 URL과 Controller의 메서드 매핑을 지원
	//5. @RequstParam : 요청 파라미터를 컨트롤러 메서드의 매개변수에 바인드
	//						단 Content-type이 application/x-www-form-urlEncoded 인 경우에만 가능
	//						FormHttpMessageConverter가 동작
	//6. @ModelAttribute : 요청 파라미터를 setter사용해 메서드 매개변수에 선언된 객체에 바인드,
	//						Model객체의 attribute로 자동으로 저장
	//7. @RequestBody : 요청 body를 읽어서 자바의 객체에 바인드
	//						application/x-www-form-urlEncoded를 지원하지 않는다.
	//8. @RequestHeader : 요청 헤더를 메서드의 매개변수에 바인드
	//9. @SessionAttribute : 원하는 session의 속성값을 매개변수에 바인드
	//10. @CookieVariable : 원하는 cookie값을 매개변수에 바인드
	//11. @PathVariable : url 템플릿에 담긴 파라미터값을 매개변수에 바인드
	//12. @ResponseBody : 메서드가 반환하는 값을 응답 body에 작성
	//13. Servlet객체를 컨트롤러의 매개변수에 선언해 주입받을 수 있다.
	//	HttpServletRequest, HttpServletResponse, HttpSession

	@GetMapping("/")
	public String index() {
		
		//Controller 메서드의 return타입
		//void  : 해당 메서드가 호출된 url의 경로와 같은 위치에 있는 jsp파일로 요청을 재지정
		//			요청 url : /index/index -> WEB-INF/views/index/index.jsp
		//String : 반환하는 값이 jsp파일의 위치, return "index/index" -> WEB-INF/views/index/index.jsp
		//ModelAndView : Model객체 + view(jsp파일의 경로)
		return "index";
	}
	
}
