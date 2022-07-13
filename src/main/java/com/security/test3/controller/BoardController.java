package com.security.test3.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.security.test3.service.MainService;

@Controller
public class BoardController {

		private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
		
		@Autowired
		MainService service;
		//------------게시판------------------
		//게시판 목록 조회
		@RequestMapping("boardList.bd")
		public String boardList(HttpServletRequest req, Model model) {
			logger.info("게시판 목록 조회");
			
			service.boardList(req, model);
			return "board/boardList";
		}
		//게시판 등록 화면
		@RequestMapping("boardInsert.bd")
		public String boardInsert() {
			logger.info("게시판 등록 화면");
			return "board/boardInsert";
		}
		//게시판 등록 처리
		@RequestMapping("boardInsertAction.bd")
		public void boardInsertAction(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
			logger.info("게시판 등록 처리");
			
			service.boardInsertAction(req, model);
			String viewPage = req.getContextPath() +"/boardList.bd";
			res.sendRedirect(viewPage);
		}
		//게시판 상세 조회
		@RequestMapping("boardDetail.bd")
		public String boardDetail(HttpServletRequest req, Model model) {
			logger.info("게시판 상세 조회");
			
			service.boardDetail(req, model);
			return "board/boardDetail";
		}
		//게시판 수정 화면
		@RequestMapping("boardUpdate.bd")
		public String boardUpdate(HttpServletRequest req, Model model) {
			logger.info("게시판 수정 화면");
			
			service.boardUpdate(req, model);
			return "board/boardUpdate";
		}
		//게시판 수정 처리
		@RequestMapping("boardUpdateAction.bd")
		public String boardUpdateAction(@RequestParam("pageNum") String pageNum, @RequestParam("searchContent") String searchContent, HttpServletRequest req, Model model, RedirectAttributes redirect) {
			logger.info("게시판 수정 처리");
			
			int board_no = Integer.parseInt(req.getParameter("board_no"));
			
			service.boardUpdateAction(req, model);
			req.setAttribute("board_no", board_no); 
			
			model.asMap().clear();
			redirect.addAttribute("pageNum", pageNum);
			redirect.addAttribute("searchContent", searchContent);
			
			if(searchContent=="") {
				return "redirect:boardList.bd";
			}else {
				return "redirect:boardSearch.bd";
			}
		}

		//게시판 삭제 처리
		@RequestMapping("boardDeleteAction.bd")
		public String boardDeleteAction(@RequestParam("crtPage") String pageNum, @RequestParam("searchContent") String searchContent, HttpServletRequest req, Model model, RedirectAttributes redirect) {
			logger.info("게시판 삭제 처리");
			
			service.boardDeleteAction(req, model);
			model.asMap().clear();
			redirect.addAttribute("pageNum", pageNum);
			redirect.addAttribute("searchContent", searchContent);
			
			if(searchContent=="") {
				return "redirect:boardList.bd";
			}else {
				return "redirect:boardSearch.bd";
			}
		}
		
		//게시판 검색
		@RequestMapping("boardSearch.bd")
		public String boardSearch(HttpServletRequest req, Model model) {
			logger.info("게시판 검색");
			
			service.boardSearch(req, model);
			return "board/boardList";
		}
}
