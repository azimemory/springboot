package com.kh.toy.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.toy.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	
}
