package com.kh.toy.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import com.kh.toy.member.MemberService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity //Spring Security의 기본설정 대신 선언된 클래스에서 설정한 내용을 시큐리티에 적용
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final DataSource dataSource;
	private final MemberService memberService;
	private final OAuthSuccessHandler oAuthSuccessHandler;
	private final OAuthFailureHadnler oAuthFailureHadnler;
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
	
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.mvcMatchers("/static/**")
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.mvcMatchers(HttpMethod.GET,"/member/mypage", "/member/logout").authenticated()
			.anyRequest().permitAll()
			.and()
			.oauth2Login();
		
		http.formLogin()
			.loginProcessingUrl("/member/login")
			.usernameParameter("userId")
			.loginPage("/member/login")
			.defaultSuccessUrl("/");
		
		http.oauth2Login()
		.loginPage("/member/login")
		.successHandler(oAuthSuccessHandler)
		.failureHandler(oAuthFailureHadnler);
		
		http.logout()
		.logoutUrl("/member/logout")
		.logoutSuccessUrl("/member/login");
		
		http.rememberMe()
		.userDetailsService(memberService)
		.tokenRepository(tokenRepository());
		
		http.csrf().ignoringAntMatchers("/mail");
	}
	
	
	
	
	
	
	

}
