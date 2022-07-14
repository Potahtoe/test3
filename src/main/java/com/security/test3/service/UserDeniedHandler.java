package com.security.test3.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

//접근 권한
public class UserDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.info("<<< UserDeniedHandler - handle 진입 >>>");
		logger.info("sessionId : " + request.getSession().getAttribute("sessionID"));
		
		request.setAttribute("errMsg", "관리자만 접근할 수 있는 페이지입니다.");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/member/accessDenied.jsp");
		dispatcher.forward(request, response);
		
	}
}
