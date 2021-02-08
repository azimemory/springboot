package com.kh.boot.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//WebSecurityConfigurerAdapter : WebSecurityConfigurer 인스턴스를 생성하기 위한 추상클래스
//메서드를 재지정하여 사용자가 보안설정을 지정한다. 그 외의 부분은 AbstractHttpConfiger의 조회 결과를 자동으로 적용

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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
	
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//정적자원에 대한 권한을 모두에게 허용
		//atCommonLocations() 메서드를 통해 StaticResourceLocation Enum을 확인해보면 
		//permitAll이 적용될 경로를 확인할 수 있다.
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
		.mvcMatchers(HttpMethod.GET,"/login","/join").permitAll()
		.mvcMatchers(HttpMethod.POST,"/join").permitAll()
		.mvcMatchers(HttpMethod.GET,"/admin").hasRole("ADMIN")
		.anyRequest().authenticated();
		
		http.formLogin()
		        .loginPage("/login").permitAll()
		        .defaultSuccessUrl("/", true);
		
		http.logout().logoutSuccessUrl("/").permitAll();
		
		http.sessionManagement()
			.sessionFixation()
			.migrateSession();
	}
}	

	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

