package com.kh.toy.common.util.jpa;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;

public class EntityUtils<T> {
	
	private T entity;
	private Map<String,Object> map;
	
	protected EntityUtils(EntityUtilsBuilder<T> builder){
		this.entity = builder.entity;
		this.map = builder.map;
	}
	
	public T mergeEntityWithMap() {
		try {
			Field[] entityFields  = getFields(entity);
			BeanUtilsBean beanUtil = new BeanUtilsBean();
			for (Field field : entityFields) {
				if(map.keySet().contains(field.getName())) {
					beanUtil.copyProperty(entity, field.getName(), map.get(field.getName()));
				}
			}
		}catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new ToAlertException(ErrorCode.CODE_500,e);
		}
		return entity;
	}
	
	private Field[] getFields(T t) {
		Field[] fields  = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			//private인 속성에 접근할 수 있도록 설정
			field.setAccessible(true);
		}
		return fields;
	}
}
