package com.kh.toy.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.HandlableException;
import com.kh.toy.common.util.file.FileUtil;
import com.kh.toy.common.util.pagination.Paging;
import com.kh.toy.entity.Board;
import com.kh.toy.entity.FileInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService{
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public void persistBoard(List<MultipartFile> multiparts, Board board) {
		
		List<FileInfo> fileInfos = new ArrayList<>();
		FileUtil fileUtil = new FileUtil();
		
		for (MultipartFile multipartFile : multiparts) {
			if(!multipartFile.isEmpty()) {
				fileInfos.add(fileUtil.fileUpload(multipartFile));
			}
		}
		
		board.setFiles(fileInfos);
		boardRepository.save(board);
	}

	public Board findBoardByIdx(Long bdIdx) {
		return boardRepository.findById(bdIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR));
	}

	public Map<String, Object> findBoardsByPage(int pageNumber) {
		
		int cntPerPage = 5;
		Page<Board> page = boardRepository.findAll(PageRequest.of(pageNumber-1, cntPerPage, Direction.DESC, "bdIdx"));
		List<Board> boards = page.getContent();
		Paging pageUtil = Paging.builder()
					.url("/board/board-list")
					.total((int)boardRepository.count())
					.cntPerPage(cntPerPage)
					.blockCnt(10)
					.curPage(pageNumber)
					.build();
		
		return Map.of("boardList", boards, "paging", pageUtil);
	}

	@Transactional
	public void modifyBoard(Board board, List<MultipartFile> fileList, List<Long> removeFlIdx) {
		
		Board boardEntity = boardRepository
						.findById(board.getBdIdx())
									.orElseThrow(() -> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR));
		
		FileUtil util = new FileUtil();
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		boardEntity.getFiles().removeIf(e ->{ 
			if(removeFlIdx.contains(e.getFlIdx())) {
				e.setIsDel(true);
				util.removeFile(e.getDownloadPath());
				return true;
			}
			return false;
		});
		
		fileList.forEach(e -> {
			if(!e.isEmpty()) {
				FileInfo file = util.fileUpload(e);
				boardEntity.getFiles().add(file);
			}
		});
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
}
