package com.kh.toy.common.util.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityUtilsBuilder<T> {
	protected T entity;
	protected T vo;
	protected Map<String,String> map;
	
	protected boolean ignoreJVMDeafultSetting = true;
	protected List<String> ignoreProperties = new ArrayList<String>();
	
	
	public EntityUtilsBuilder<T> entity(T entity) {
		this.entity = entity;
		return this;
	}
	
	public EntityUtilsBuilder<T> vo(T vo) {
		this.vo = vo;
		return this;
	}
	
	public EntityUtilsBuilder<T> map(Map<String,String> map) {
		this.map = map;
		return this;
	}
	
	public EntityUtilsBuilder<T> ignoreJVMDeafultSetting(boolean flg) {
		this.ignoreJVMDeafultSetting = flg;
		return this;
	}
	
	public EntityUtilsBuilder<T> ignoreProperties(String properties) {
		this.ignoreProperties.add(properties);
		return this;
	}
	
	public EntityUtils<T> build(){
		return new EntityUtils<T>(this);
	}
}
