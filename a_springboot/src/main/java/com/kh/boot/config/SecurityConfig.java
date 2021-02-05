package com.kh.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//WebSecurityConfigurerAdapter : WebSecurityConfigurer 인스턴스를 생성하기 위한 추상클래스
//메서드를 재지정하여 사용자가 보안설정을 지정한다. 그 외의 부분은 AbstractHttpConfiger의 조회 결과를 자동으로 적용
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//우리가 해볼 것 : 회원가입 / 로그인 / 권한관리
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().anyRequest().permitAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
