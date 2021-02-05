package com.kh.boot.index.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kh.boot.member.model.vo.Member;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		return "index";
	}
	
	@RequestMapping("/thymeleaf/study")
	public String study(Model model, HttpSession session) {
		
		Member member = new Member();
		member.setUserId("hamyeongdo");
		member.setTell("010-9234-8248");
		member.setEmail("azimemory@gmail.com");
		
		Map<String,Object> score = new HashMap<>();
		score.put("java",100);
		score.put("html",50);
		score.put("css",70.0);
		score.put("js",60);
		score.put("servlet",90);
		score.put("spring",100);
		score.put("average",67);
		
		session.setAttribute("user",member);
		model.addAttribute("score", score);
		model.addAttribute("color", "blue");
		
		return "thymeleaf/study";
	}
}
