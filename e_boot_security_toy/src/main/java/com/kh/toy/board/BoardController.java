package com.kh.toy.board;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.toy.entity.Board;
import com.kh.toy.entity.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private final BoardService boardService;
	
	@GetMapping("form")
	public String boardForm() {
		return "/board/board-form";
	};
	
	@PostMapping("upload")
	public String uploadBoard(
				@RequestParam List<MultipartFile> fileList
				,@SessionAttribute("authentication") Member member
				, Board board
			) {
		
		board.setMember(member);
		boardService.persistBoard(fileList, board);
		return "redirect:/";
	}
	
	@GetMapping("detail")
	public String boardDetail(Model model, Long bdIdx) {
		Board board = boardService.findBoardByIdx(bdIdx);
		model.addAttribute("board", board);
		return "/board/board-detail";
	}
	
	
	@GetMapping("list")
	public String boardList(Model model, 
						@RequestParam(required = false, defaultValue = "1") int page) {
		
		 model.addAllAttributes(boardService.findBoardsByPage(page));
		 return "/board/board-list";
	}

	
	@GetMapping("modify")
	public String boardModify(Model model,
							long bdIdx){
		model.addAttribute("board", boardService.findBoardByIdx(bdIdx));
		return "/board/board-modify";
	}
	
	@PostMapping("modify")
	public String modifyBoard(Board board
						,@RequestParam List<MultipartFile> fileList
						,@RequestParam(required = false)
						 Optional<List<Long>> removeFlIdx) {
		
		boardService.modifyBoard(board, fileList, removeFlIdx.orElse(List.of()));
		return "redirect:/board/detail?bdIdx="+board.getBdIdx();
	}
	
	
	
	
	
	
	
	
	

}
