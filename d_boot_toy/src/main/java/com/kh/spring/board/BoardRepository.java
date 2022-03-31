package com.kh.spring.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	
}
