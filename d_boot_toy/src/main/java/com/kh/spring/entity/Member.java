package com.kh.spring.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Member {
	
   @Id
   private String userId;
   private String password;
   private String email;
   private String grade;
   private String tell;
   
   @Column(columnDefinition = "datetime default now()")
   private LocalDate rentableDate;
   
   @Column(columnDefinition = "datetime default now()")
   private LocalDate regDate;
   
   @Column(columnDefinition = "int default 0")
   private Boolean isLeave;

}
