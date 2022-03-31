package com.kh.toy.common.code;

public enum Config {
	
	//DOMAIN("https://pclass.ga"),
	DOMAIN("http://localhost:9090"),
	COMPANY_EMAIL("azimemory@gmail.com"),
	SMTP_AUTHENTICATION_ID("azimemory@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("1234"),
	UPLOAD_PATH("C:\\CODE\\after\\upload\\");

	public final String DESC;
	
	Config(String desc) {
		this.DESC = desc;
	}

	
	
}
