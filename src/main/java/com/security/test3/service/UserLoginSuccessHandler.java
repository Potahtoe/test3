package com.security.test3.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.security.test3.dto.UserVO;
import com.security.test3.page.Paging;

//로그인 성공
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserLoginSuccessHandler.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserLoginSuccessHandler(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("<<<UserLoginSuccessHandler - onAuthenticationSuccessHandler 진입 >>>");
		
		UserVO vo =(UserVO)authentication.getPrincipal();
		logger.info("UserVO : " + vo.getUsername());
		
		String authority = sqlSession.selectOne("com.security.test3.dao.MainDao.authorityCheck", authentication.getName());
		
		request.getSession().setAttribute("sessionID", authentication.getName()); //세션추가
		request.getSession().setAttribute("authority", authority);
		
		if(authority.equals("ROLE_ADMIN")) {
			//int grade=1;
			RequestDispatcher dispatcher = request.getRequestDispatcher("/boardList.do");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("errorMsg", "접근 권한이 없습니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.do");
			dispatcher.forward(request, response);
		}
	}
}
