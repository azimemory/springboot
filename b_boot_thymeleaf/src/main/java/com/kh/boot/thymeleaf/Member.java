package com.kh.boot.thymeleaf;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Member {
	
	private String userId;
	private String password;
	private String tell;
	private String grade;
	private Integer loginCnt;
	private LocalDateTime regDate;
	

	
	

}
