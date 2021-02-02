package com.kh.boot.member.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.boot.member.model.vo.Member;
import com.kh.boot.member.model.vo.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberDao {
	@Autowired
	JPAQueryFactory jpaQuery;
	QMember m = QMember.member;

	public Member selectMember(String userId) {
		Member member = jpaQuery.select(m).from(m).where(m.userId.eq(userId)).fetchOne();
		System.out.println(member);
		return member;
	}
}
