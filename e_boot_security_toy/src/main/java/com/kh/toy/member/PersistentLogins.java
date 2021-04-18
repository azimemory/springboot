package com.kh.toy.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersistentLogins {
    @Id
    @Column(length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    private String username;
    
    //매 요청마다 새로운 토큰을 만들어서 발급
    //remember-me 때 헤더에 쿠키가 있다면 그 쿠키의 series로  DB를 조회한다.
    //series가 같지만 token이 다르다면 자동로그인을 해제한다.
    @Column(nullable = false, length = 64)
    private String token;
    
    //마지막 사용으로부터 2주까지가 유효기간. 
    //2주동안 last_used가 사용되지 않으면 자동로그인이 해제된다.
    @Column(name = "last_used",nullable = false,length = 64)
    private LocalDateTime lastUsed;
}