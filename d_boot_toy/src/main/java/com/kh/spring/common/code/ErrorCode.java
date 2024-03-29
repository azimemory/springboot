package com.kh.spring.common.code;

public enum ErrorCode {
	
	DATABASE_ACCESS_ERROR("데이터베이스와 통신 중 에러가 발생하였습니다."),
	VALIDATOR_FAIL("부적절한 양식의 데이터 입니다."),
	MAIL_SEND_FAIL("이메일 발송 중 에러가 발생하였습니다."),
	HTTP_CONNECT_ERROR("HTTP 통신 중 에러가 발생하였습니다."),
	AUTHENTICATION_FAILED("유효하지 않은 인증입니다."),
	UNAUTHORIZED_PAGE("접근 권한이 없는 페이지 입니다."),
	FILE_UPLOAD_FAIL("파일업로드에 실패하였습니다."),
	REDIRECT("");
	
	public final String MESSAGE;
	public String URL;
	
	private ErrorCode(String msg) {
		this.MESSAGE = msg;
		this.URL = "/";
	}
	
	private ErrorCode(String msg, String url) {
		this.MESSAGE = msg;
		this.URL = url;
	}

	public ErrorCode setURL(String uRL) {
		URL = uRL;
		return this;
	}
	
	

}
