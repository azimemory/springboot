package com.kh.boot.member.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.kh.boot.member.model.vo.Member;
import com.kh.boot.member.model.vo.MemberAuth;
import com.kh.boot.member.model.vo.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberDao{
	@Autowired
	JPAQueryFactory query;
	QMember m = QMember.member;

	public Member selectMember(String userId) {
		Member member = query.select(m).from(m).where(m.userId.eq(userId)).fetchOne();
		return member;
	}
	
	public void insertMember(Member member) {
		query.insert(m).values(member);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
