package com.kh.toy.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	 @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/file/**")
        .addResourceLocations("file:///C:/CODE/afternoon/E_SERVLET/resources/upload/");
    }
	 
	 @Bean
	 @Order(1)
	 public FilterRegistrationBean<MultipartFilter> loggingFilter() {
	    FilterRegistrationBean<MultipartFilter> registration = new FilterRegistrationBean<>();
	    registration.setFilter(new MultipartFilter());
	    registration.addUrlPatterns("/*");
	    registration.setOrder(1);
	    return registration;
	 }

     @Bean(name = MultipartFilter.DEFAULT_MULTIPART_RESOLVER_BEAN_NAME)
     public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(1024 * 1024 * 50);
        return resolver;
     }
}
