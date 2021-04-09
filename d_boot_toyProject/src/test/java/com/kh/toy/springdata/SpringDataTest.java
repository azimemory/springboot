package com.kh.toy.springdata;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.member.Member;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringDataTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SpringDataRepository repo;
	
	List<String> ids = null;
	Member member = null;
	
	@DisplayName("JpaRepository가 제공하는 메서드")
	@Test
	public void findById() {
		repo.findById("jpaRepository")
			.orElseThrow(()-> new ToAlertException(ErrorCode.NON_EXIST_USER)); 
	}
	
	@DisplayName("JpaRepository가 제공하는 메서드")
	@Test
	public void findAll() {
		repo.findAll();
	}
	
	@DisplayName("JpaRepository가 제공하는 메서드")
	@Test
	public void findAllById() {
		repo.findAllById(ids);
	}
	
	@DisplayName("JpaRepository가 제공하는 메서드")
	@Test
	public void count() {
		repo.count();
	}
	
	@DisplayName("JpaRepository가 제공하는 메서드")
	@Test
	public void deleteById() {
		repo.deleteById(member.getUserId());
	}
}
