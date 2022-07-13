package com.security.test3.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.security.test3.service.MainService;

@Controller
public class MainController {

		private static final Logger logger = LoggerFactory.getLogger(MainController.class);
		
		@Autowired
		MainService service;

		//------------회원가입------------------
		//회원가입 화면
		@RequestMapping("signIn.do")
		public String signIn() {
			logger.info("회원가입 화면");
			
			return "member/signIn";
		}
		
		//회원가입 처리
		@RequestMapping("signInAction.do")
		public String signInAction(HttpServletRequest req, Model model) {
			logger.info("회원가입 처리");
			
			service.signInAction(req, model);
			return "member/signInAction";
		}
		
		//회원가입 성공 시
		@RequestMapping("signInSuccess.do")
		public String signInSuccess(HttpServletRequest req, Model model) {
			logger.info("회원가입 성공");
			
			//signInAction.jsp에서 들고 온 값 불러오기
			int cnt = Integer.parseInt(req.getParameter("insertCnt"));
			
			model.addAttribute("selectCnt", cnt);
			
			return "member/login";
		}
		
		//------------로그인------------------
		//로그인 화면
		@RequestMapping("login.do")
		public String login() {
			logger.info("로그인 화면");
			return "member/login";
		}
		
		//로그인 처리
		@RequestMapping("loginAction.do")
		public String loginAction(HttpServletRequest req, Model model) {
			logger.info("로그인 처리");
			
			service.loginAction(req, model);
			return "member/loginAction";
		}
		
		//로그인 처리
		@RequestMapping("logout.do")
		public String logout(HttpServletRequest req) {
			logger.info("로그아웃");
			
			req.getSession().invalidate();
			return "member/login";
		}
}
