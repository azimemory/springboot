package com.kh.boot.thymeleaf;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

	@GetMapping("study/thymeleaf")
	public void sendDataForStudy(Model model, HttpSession session) {
		
		//Vo
		Member member = new Member();
		member.setUserId("thymeleafStudent");
		member.setPassword("1234");
		member.setGrade("일반");
		member.setTell("010-0000-1111");
		member.setLoginCnt(10);
		member.setRegDate(LocalDateTime.parse("2022-03-30"));
		
		Map<String, Object> score = new LinkedHashMap<>();
		score.put("java",100);
		score.put("html",88);
		score.put("css",65);
		score.put("js",91);
		score.put("servlet",77);
		score.put("spring",100);
		score.put("average",87);
		
		model.addAttribute("score", score);
		model.addAttribute("color", "blue");
		session.setAttribute("userInfo", member);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
