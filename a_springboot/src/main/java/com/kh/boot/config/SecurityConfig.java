package com.kh.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//WebSecurityConfigurerAdapter : 사용자가 자체적으로 보안 구성을 하고 싶을 때 사용.
//								해당 클래스를 작성하면 기본 자동 구성이 비활성화 된다.
//우리가 해볼 것 : 회원가입 / 로그인 / 권한관리
	@Override
	public void configure(HttpSecurity http) throws Exception{
		//http.authorizeRequests().anyRequest().permitAll();
	}
}
