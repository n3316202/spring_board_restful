package edu.bit.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

//spring v4.0 이전버전 (@Controller + @ResponseBody )
@Log4j
@NoArgsConstructor
@Controller
public class RestBoardSpring4BeforeController {

	@Autowired
	private BoardService boardService;

	// home.jsp
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String home()
	 * { return "home"; }
	 */

	// before.json �쑝濡� �젒�냽�떆, josn�쑝濡� �몴�떆�맖
	@ResponseBody
	@RequestMapping("/rest/before")
	public List<BoardVO> before() {

		log.info("/rest/before");
		List<BoardVO> list = boardService.getList();

		return list;
	}
}
