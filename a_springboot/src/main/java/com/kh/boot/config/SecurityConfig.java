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
	
	//configure(HttpSecurity http)
	//	HttpSecurity : 특정 http 요청에 대한 웹기반 보안을 구성
	//	
	//configure(WebSecurity web)
	//	WebSecurity : Spring Security Filter Chain을 생성하는 객체
	
	//configure(AuthenticationManagerBuilder auth)
	//	AuthenticationManagerBuilder : Authentication Manager를 생성하는데 사용
	//	메모리 인증, LDAP 인증, JDBC 기반 인증, UserDetailsService 추가 및 Authentication Provider's 추가
	//	Authentication Provider : 인증을 담당하여 처리하는 객체, 인증 방법별로 존재하며 인증요청이 자신의 방식으로
	//  처리해야하는 요청이라면 인증처리를 진행한다. 
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
//		ant-style pattern에 매칭되는 url 요청에 대하여 권한을 관리할 수 있다.
		//	ant-style pattern 
		//		? : 1개의 문자와 매칭
		//		* : 0개 이상의 문자와 매칭
		//		** : 0개 이상의 디렉토리와 파일 매칭
		http.authorizeRequests().antMatchers("/index").permitAll();
		
		http.formLogin().loginPage("/member/login")
		.loginProcessingUrl("/member/loginimpl")
		.usernameParameter("userId")
		.passwordParameter("pw")
		.defaultSuccessUrl("/index")
		.failureForwardUrl("/member/login");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
