package com.kh.boot.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.querydsl.jpa.impl.JPAQueryFactory;
import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class JPAConfig {
	
	@Autowired
	OracleDataSource oracleDataSource;
	
	@PersistenceContext
	private EntityManager em;
	
	@Bean 
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(em);
	}
}
