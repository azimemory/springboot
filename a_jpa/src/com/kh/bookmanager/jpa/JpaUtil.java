package com.kh.bookmanager.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class JpaUtil {
	
	//EntityManagerFactory는 threadSafe하기 때문에 static에 올려 놓고 사용하거나
	//SingleTon으로 사용해도 안전.
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("a_jpa");

	//EntityManager는 threadSafe하지 않기 때문에 반드시 지역(메서드)안에서 생성
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	
	public static Session createSession() {
		return EMF.createEntityManager().unwrap(Session.class);
	}
}
