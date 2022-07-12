package com.security.test3.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.security.test3.dto.UserVO;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserLoginSuccessHandler(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("<<<UserLoginSuccessHandler - onAuthenticationSuccessHandler 진입 >>>");
		
		UserVO vo =(UserVO)authentication.getPrincipal();
		System.out.println("UserVO : " + vo.getUsername());
		
		String msg = authentication.getName() + "님 환영합니다.";
		String authority = sqlSession.selectOne("com.security.test3.dao.CustomerDAO.authorityCheck", authentication.getName());
		
		request.setAttribute("msg", msg);
		request.getSession().setAttribute("sessionID", authentication.getName()); //세션추가
		request.getSession().setAttribute("authority", authority);
		
		int grade = 0;
		if(authority.equals("ROLE_ADMIN")) {
			grade=1; //고객 로그인 시
			RequestDispatcher dispatcher = request.getRequestDispatcher("/boardList.do");
			dispatcher.forward(request, response);
		}
	}
}
