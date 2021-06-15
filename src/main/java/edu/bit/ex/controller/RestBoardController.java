package edu.bit.ex.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//REST : Representational State Transfer
//하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념

//http://localhost/spring02/list?bno=1 ==> url+파라미터
//http://localhost/spring02/list/1 ==> url
//RestController은 스프링 4.0부터 지원
//@Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무

@Log4j
@AllArgsConstructor
@RestController
@RequestMapping("/restful/*")
public class RestBoardController {

	private BoardService boardService;

	// 1. list(처음 진입 화면이므로 화면이 깜박여도 상관없으므로 @Controller방식으로 접근 - veiw(화면)를 리턴
	@GetMapping("/board")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("rest/rest_list");
		mav.addObject("list", boardService.getList());

		return mav;
	}

	@GetMapping("/board/{bid}")
	public ModelAndView rest_content_view(BoardVO boardVO, ModelAndView mav) {

		log.info("rest_content_view");
		mav.setViewName("rest/rest_content_view");
		mav.addObject("content_view", boardService.get(boardVO.getBid()));
		return mav;
	}
	// ,
	// @RequestParam("bContent") String bContent
	/*
	 * @PutMapping("/board/{bId}") public ResponseEntity<String>
	 * rest_update(@PathVariable("bId") int bId,HttpServletRequest request){
	 * 
	 * String bContent = request.getParameter("bContent"); ResponseEntity<String>
	 * entity = null;
	 * 
	 * log.info("rest_update.."); try { int rn = boardService.modify(boardVO);
	 * log.info("update �Ѿ�� ����:::::" + rn); entity = new
	 * ResponseEntity<String>("SUCCESS", HttpStatus.OK); } catch (Exception e) {
	 * e.printStackTrace(); entity = new ResponseEntity<String>(e.getMessage(),
	 * HttpStatus.BAD_REQUEST); }
	 * 
	 * 
	 * return entity; // ���� ó�� HTTP ���� �޽��� ���� }
	 */

	@PutMapping("/board/{bId}")
	public ResponseEntity<String> rest_update(@RequestBody BoardVO boardVO, ModelAndView modelAndView) {

		ResponseEntity<String> entity = null;
		log.info("rest_update..");
		
		try {
			
			boardService.modify(boardVO);

			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

	@DeleteMapping("/board/{bId}")
	public ResponseEntity<String> rest_delete(BoardVO boardVO, Model model) {
		ResponseEntity<String> entity = null;
		log.info("rest_delete..");
		try {
			boardService.remove(boardVO.getBid());
			// ������ �����ϸ� ���� ���¸޽��� ����
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// ��� ������ �����ϸ� ���� ���¸޽��� ����
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// ���� ó�� HTTP ���� �޽��� ����
		return entity;

	}
}
