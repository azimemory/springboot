package com.kh.toy.common.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kh.toy.common.exception.CustomException;

@Controller
//지정한 패키지 내의 모든 컨트롤러들의 공통관심사를 처리하는 클래스
@ControllerAdvice(basePackages = {"com.kh.toy","common"})
public class ExceptionController {
	
	Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(CustomException.class)
	public String buisnessExceptionHandler(CustomException e, Model model) {
		model.addAttribute("alertMsg", e.error.msg);
		model.addAttribute("url", e.error.url);
		return "common/result";
	}
	
	//응답 상태코드 지정
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		e.printStackTrace();
		model.addAttribute("alertMsg", "데이터베이서 접근 도중 예외가 발생하였습니다.");
		model.addAttribute("url", "/");
		return "common/result";
	}
	
	
	
	
	
	
	
	
	

}
