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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final MemberService memberService;
    private final DataSource dataSource;
    
    public SecurityConfig(MemberService memberService,  DataSource dataSource) {
    	this.memberService = memberService;
    	this.dataSource = dataSource;
    }
    
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
    
    //WebSecurity는 FilterChainProxy를 생성하는 필터입니다.
    @Override
    public void configure(WebSecurity web) throws Exception {
    	 //정적 자원에 대해서는 security 설정을 적용하지 않음
    	 web.ignoring()
    	 .mvcMatchers("/static/**")
    	 .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/member/login", "/member/join", "/member/idcheck").permitAll()
                .mvcMatchers(HttpMethod.POST, "/member/mailauth", "/mail", "/member/loginimpl","/member/idcheck").permitAll()
                .antMatchers("/member/joinimpl/**").permitAll()             
                .anyRequest().authenticated();
       
        http.formLogin()
        		.loginProcessingUrl("/member/loginimpl")
        		.usernameParameter("userId")
                .loginPage("/member/login").permitAll()
                .defaultSuccessUrl("/", true);

        http.logout()
        		.logoutUrl("/member/logout")
                .logoutSuccessUrl("/index");
     
        http.csrf()
        	.ignoringAntMatchers("/mail/**");
        
        http.rememberMe()
        	.userDetailsService(memberService)
        	.tokenRepository(tokenRepository());
    }
    
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}


