package com.kh.toy.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.toy.member.Member;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
	public Board findByBdIdxAndMember(String bdIdx, Member member);
}
