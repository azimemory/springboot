package com.kh.toy.common;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import com.kh.toy.common.util.jpa.EntityUtils;
import com.kh.toy.common.util.jpa.EntityUtilsBuilder;
import com.kh.toy.member.Member;

@SpringBootTest
public class jpaUtilTest {
	
	@Test
	public void mergeEntityWithMapTest() {
		Member entity = new Member();
		Map<String,Object> map = new HashMap<>();
		
		entity.setUserId("azimemory");
		entity.setPassword("123qwe!@#");
		
		map.put("email","azimemory@gmail.com");
		map.put("tell","010-1111-222");
		
		entity = new EntityUtilsBuilder<Member>()
				.entity(entity)
				.map(map)
				.build()
				.mergeEntityWithMap();
		
		System.out.println(entity);
		System.out.println(map);
	}
	
	
	
	
	
}
