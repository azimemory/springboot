package com.kh.toy.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AdminMemberController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	@GetMapping("member/member-list")
	public void searchAllMembers(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminMemberService.findAllMembers(page));
	}
	
	
	
	
	
	
	
	
	
}
