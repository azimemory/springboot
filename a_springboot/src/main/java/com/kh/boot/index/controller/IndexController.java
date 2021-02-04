package com.kh.boot.index.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(Model model) {
		
		List<String> subject = new ArrayList<String>();
		subject.add("java");
		subject.add("html");
		subject.add("css");
		subject.add("js");
		subject.add("servlet");
		subject.add("spring");
		
		Map<String,Object> score = new HashMap<>();
		score.put("java",100);
		score.put("html","50점");
		score.put("css",70.0);
		score.put("js",false);
		score.put("servlet",null);
		score.put("spring",100);
		
		model.addAttribute("subject", subject);
		model.addAttribute("score", score);
		model.addAttribute("userId", "hamyeongdo");
		
		return "thymeleaf/study.html";
	}
}
