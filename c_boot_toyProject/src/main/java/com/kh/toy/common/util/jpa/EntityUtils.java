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
	private T vo;
	private Map<String,String> map;
	private boolean ignoreJVMDeafultSetting;
	private List<String> ignoreProperties;
	
	protected EntityUtils(EntityUtilsBuilder<T> builder){
		this.entity = builder.entity;
		this.vo = builder.vo;
		this.map = builder.map;
		this.ignoreJVMDeafultSetting = builder.ignoreJVMDeafultSetting;
		this.ignoreProperties = builder.ignoreProperties;
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
	
	public T mergeEntityWithVo() {
		try {
			//entity와 vo의 필드를 필드배열에 저장
			Field[] entityFields  = getFields(entity);
			Field[] voFields = getFields(vo);
		
			for (int i = 0; i < entityFields.length; i++){
				if(voFields[i].getName().equals("serialVersionUID")) {
					continue;
				}
				
				if(ignoreJVMDeafultSetting) {
					if(checkPropertiesValueIsJvmDefaultSetting(voFields[i])) {
						continue;
					}
				}
				
				if(ignoreProperties.contains(entityFields[i].getName())) {
					continue;
				}
				
				entityFields[i].set(entity, voFields[i].get(vo));
			}
		} catch (IllegalArgumentException | IllegalAccessException e){
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
	
	private boolean checkPropertiesValueIsJvmDefaultSetting(Field voField) throws IllegalArgumentException, IllegalAccessException {
		if(voField.get(vo) == null) {
			return true;
		}
		
		if(voField.get(vo).equals(0)) {
			return true;
		}
		
		if(voField.get(vo).equals('\u0000')) {
			return true;
		}
		
		return false;
	}
	
	
}
