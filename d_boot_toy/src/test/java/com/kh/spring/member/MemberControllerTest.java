package com.kh.spring.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.kh.spring.member.validator.JoinForm;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("아이디 중복검사 - 성공")
	public void idCheckWithSuccess() throws Exception{
		mockMvc.perform(get("/member/id-check")
				.param("userId", "qweuqoiwekjasdhaqw"))
		.andExpect(status().isOk())
		.andExpect(content().string("available"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("이메일 발송 이후 회원가입 완료처리")
	public void joinImpl() throws Exception{

		JoinForm form = new JoinForm();
		form.setUserId("test");
		form.setPassword("123qwe!@#");
		form.setTell("01000001111");
		form.setEmail("azimemory@gmail.com");
		
		mockMvc.perform(get("/member/join-impl/1234")
				.sessionAttr("persistToken", "1234")
				.sessionAttr("persistUser", form))
		.andExpect(status().is3xxRedirection())
		.andDo(print());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
