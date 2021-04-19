package com.kh.toy.member;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.common.util.jpa.EntityUtils;
import com.kh.toy.common.util.jpa.EntityUtilsBuilder;
//@Controller : 
//@RequestMapping : 해당 메서드와 매핑시킬 요청 url을 지정, http method 상관없음
//@GetMapping : 해당 메서드와 매핑시킬 요청 url을 지정, Get method만 매핑
//@PostMapping : 해당 메서드와 매핑시킬 요청 url을 지정, Post method만 매핑
//@RequestParam : FormHttpMessageConverter가 동작, 요청 파라미터의 값을 전달할 매개변수 앞에 작성, Map을 사용해 모든 파라미터를 바인드 할 수 있다.
//			속성 >> name : 파라미터명, 매개변수이름과 파라미터명이 같을 경우 생략가능
//				   required : 파라미터 필수여부
//				   defaultValue : 파라미터가 없거나 값이 비었을 경우 세팅할 값
//				   value : @RequestParam("파라미터명") 형태로 사용할 때 쓰이는 name 의 별칭
//@RequestBody : MappingJacksonHttpMessageConverter가 동작, json을 객체로 바인드해준다.
//				MultiValueMap 앞에 붙을 경우 FormHttpMessageConverter 가 메세지를 변환한다.
//@ModelAttribute : FormHttpMessageConverter가 동작, 요청파라미터를 객체에 바인드 시킨 뒤 응답할 model 객체에 추가한다.
//@ResponseBody : 응답바디에 직접 데이터를 작성
import com.kh.toy.member.validator.MemberValidator;

//1. 해당 클래스를 applicationContext에 빈으로 등록
//2. Controller와 관련된 annotation을 사용할 수 있게 해준다.
//	 @RequestMapping : 컨트롤러의 메서드와 매핑시킬 요청 url을 지정, http method 상관없음
//	 @GetMapping : 컨트롤러의 메서드와 매핑시킬 요청 url을 지정 get method만 매핑
//   @PostMapping : 컨트롤러의 메서드와 매핑시킬 요청 url을 지정 post method만 매핑
//	 @RequestParam : 요청 파라미터를 컨트롤러 메서드의 매개변수에 바인드
//				FormHttpMessageConverter가 동작, Content-type : form-url-encoded	
//				속성 >> name : 요청파라미터명, 컨트롤러메서드의 매개변수명과 요청파라미터명이 같으면 생략 가능
//						required : 필수 여부 default : true
//						defaultValue : 파라미터가 없거나, 빈값이 넘어왔을 때 세팅할 기본 값
//						value : name alias, ex) @RequestParam("userId")
//	 @RequestBody : json포멧으로 넘어온 요청바디를 읽어서 자바의 객체에 바인드
//				MappingJacksonHttpMessageConverter가 동작 Content-type : application/json
//	 @ModelAttribute : 요청 파라미터를 VO에 바인드, VO에 바인드함과 동시에 Model에 VO를 담는다.
//	 @RequestHeader : 요청해더를 컨트롤러의 매개변수에 바인드
//	 @SessionAttribute : 원하는 Session의 속성을 컨트롤러의 매개변수에 바인드
//	 @CookieValue : 원하는 Cookie의 값을 컨트롤러의 매개변수에 바인드
//	 @PathVariable : url 템플릿에 담긴 파라미터값을 컨트롤러의 매개변수에 바인드
//   @ResponseBody : 컨트롤러의 메서드 위에 작성, 메서드가 반환하는 값을 응답바디에 직접 넣어준다.

//*** Servlet 객체(HttpServletRequest, HttpServletResponse, HttpSession) 들을
// 컨트롤러 메서드의 매개변수로 전달받을 수 있다.
// HttpEntity, RequestEntity, ResponseEntity

@Controller
@RequestMapping("member")
public class MemberController {
	
	private final MemberService memberService;
	private final MemberValidator memberValidator;
	
	public MemberController(MemberService memberService
						,MemberValidator memberValidator) {
		this.memberService = memberService;
		this.memberValidator = memberValidator;
	}

	//InitBinder : WebDataBinder를 초기화하는 메서드를 식별하는 주석
	//		value : webDataBinder가 적용될 파라미터 명 또는 Model의 attribute 이름
    @InitBinder("member")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(memberValidator);
    }
	
	//반환형이 void일 경우 요청온 viewResolver에 등록된 prefix를 기준으로
	//url과 같은 경로에 있는 jsp로 요청을 재지정한다.
	@GetMapping("join")
	public void join(Model model, Member member) {}
	
	@PostMapping("idcheck")
	@ResponseBody
	public String confirmId(String userId) {
		if(memberService.selectMemberById(userId) != null) {
			return "fail";
		}
		return "success";
	}
	
	@PostMapping("mailauth")
	public String authenticateEmail
					(@Valid Member member
					, Errors error //반드시 Errors 변수를 @Valid 변수 바로 뒤에 작성
					, HttpSession session
					, Model model) {
		
		if(error.hasErrors()) {
			return "member/join";
		}
		
		session.setAttribute("persistInfo", member);
		session.setAttribute("sessionId", session.getId());
		
		memberService.authenticateEmail(member,session.getId());
		
		model.addAttribute("msg", "이메일이 발송되었습니다.");
		model.addAttribute("url","/index");
		
		return "common/result";
	}
	
	//동적 url : joinimpl/ 로 시작하는 모든 요청을 해당 메서드로 받는다.
	//@PathVariable : 동적 url의 패스변수값을 받을 변수 앞에 작성
	@GetMapping("joinimpl/{sessionId}")
	public String joinImpl(
						  @PathVariable("sessionId") String sessionId
						 , HttpSession session
						 ,@SessionAttribute("persistInfo")Member persistInfo 
						  , Model model) {
		
		if(!session.getId().equals(sessionId)) {
			throw new ToAlertException(ErrorCode.EXPIRATION_AUTH);
		}
		
		memberService.saveMember(persistInfo);
		session.removeAttribute("persistInfo");
		return "member/login";
	}
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("mypage")
	public void myPage() {}
	
	@PostMapping("modify")
	public String modify(@RequestParam Map<String,Object> commandMap
						,@AuthenticationPrincipal MemberAccount memberAccount){
		
		Member userInfo = memberAccount.getMember();
		userInfo = new EntityUtilsBuilder<Member>()
				.entity(userInfo)
				.map(commandMap)
				.build()
				.mergeEntityWithMap();
		
		memberService.updateMember(userInfo);
		return "member/mypage";
	}
	
	@GetMapping("leave")
	public String leave(@AuthenticationPrincipal MemberAccount memberAccount){
		if(memberAccount.isEnabled()) {
			memberService.updateMemberToLeave(memberAccount.getUsername());
		}
		return "index/index";
	}
}
