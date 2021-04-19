package com.kh.toy.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.common.util.file.FileEntity;
import com.kh.toy.common.util.file.FileUtil;
import com.kh.toy.common.util.jpa.EntityUtilsBuilder;
import com.kh.toy.common.util.paging.Paging;
import com.kh.toy.member.Member;

@Service
public class BoardService {
	
	private BoardRepository repo;
	
	public BoardService(BoardRepository repo) {
		this.repo = repo;
	}
	
	@Transactional
	public void saveBoard(Board board, List<MultipartFile> files) {
		//파일업로드를 위해 FileUtil.fileUpload() 호출
		List<FileEntity> fileEntities = new FileUtil().fileUpload(files);
		board.setFileEntities(fileEntities);
		board = repo.save(board);
	}

	public Map<String, Object> findBoardList(PageRequest page) {
		 Map<String, Object> commandMap = new HashMap<String, Object>();
		
		 //현재 페이지에 필요한 게시물 목록
		 Page<Board> blist = repo.findAll(page);
		 Paging paging = Paging.builder()
				 .blockCnt(5)
				 .cntPerPage(page.getPageSize())
				 .currentPage(page.getPageNumber()+1)
				 .total((int)blist.getTotalElements())
				 .type("board")
				 .build();
		 
		 commandMap.put("blist", blist.getContent());
		 commandMap.put("paging", paging);
		 return commandMap;
	}

	public Board findBoardDetail(String bdIdx) {
		return repo.findById(bdIdx)
				.orElseThrow(()-> new ToAlertException(ErrorCode.NON_EXIST));
	}
	
	public Board findBoardToModify(String bdIdx, Member member) {
		return Optional.ofNullable(repo.findByBdIdxAndMember(bdIdx, member))
				.orElseThrow(() -> new ToAlertException(ErrorCode.NON_EXIST));
	}
	
	@Transactional
	public void updateBoard(Map<String,Object> commandMap, List<String> delFiles, List<MultipartFile> files, Member member) {
		FileUtil fileUtil = new FileUtil();
		
		//영속성 컨택스트에서 게시글 정보를 받아온다.
		Board board = Optional.ofNullable(repo
				.findByBdIdxAndMember(commandMap.get("bdIdx").toString(), member))
				.orElseThrow(()-> new ToAlertException(ErrorCode.NON_EXIST));
		
		//변경된 게시글 내용을 게시글 엔티티에 반영
		board = new EntityUtilsBuilder<Board>()
				.entity(board)
				.map(commandMap)
				.build()
				.mergeEntityWithMap();
		
		//board 엔티티에서 사용자가 삭제한 파일 제거 +  파일 삭제
		if(delFiles != null) {
			board.getFileEntities().removeIf(file -> {
				if(delFiles.contains(file.getFileIdx())) {
					fileUtil.deleteFile(file.getFullPath(),file.getRenameFileName());
					return true;
				}
				return false;
			});
		}
		
		List<FileEntity> fileEntities = fileUtil.fileUpload(files); //수정할 때 추가한 파일 업로드
		board.getFileEntities().addAll(fileEntities);
	}

	@Transactional
	public void deleteBoard(String bdIdx, Member member) {
		Board board = Optional.ofNullable(repo
				.findByBdIdxAndMember(bdIdx, member))
				.orElseThrow(()-> new ToAlertException(ErrorCode.NON_EXIST));
		
		FileUtil fileUtil = new FileUtil();
		for (FileEntity file : board.getFileEntities()) {
			fileUtil.deleteFile(file.getFullPath(),file.getRenameFileName());
			repo.delete(board);
		}
	}
}
