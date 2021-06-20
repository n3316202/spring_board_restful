package edu.bit.ex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@RequestMapping("/ajax/*")
@Controller
public class AjaxBoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public String ajaxList() {

		log.info("ajaxList()..");
		
		return "ajax/ajaxDelete";
	}
	
	@ResponseBody
	@GetMapping("/delete")
	public String ajaxDelete(BoardVO boardVO) {

		log.info("ajaxList()..");
		log.info(boardVO);
		boardService.remove(boardVO.getBid());
		 
		return "SUCCESS";
	}
}
