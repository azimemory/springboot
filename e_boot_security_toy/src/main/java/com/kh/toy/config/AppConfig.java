package com.kh.toy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory requestFactory =
				new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(5000);

		return new RestTemplate(requestFactory);
	}
}
