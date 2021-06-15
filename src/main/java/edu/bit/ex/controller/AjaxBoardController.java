package edu.bit.ex.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@RequestMapping("/ajax/*")
@Controller
public class AjaxBoardController {

	@GetMapping("/list")
	public String ajaxList() {

		log.info("ajaxList()..");
		
		return "ajax/ajaxList";
	}
}
