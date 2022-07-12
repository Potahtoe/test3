package com.security.test3.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//로그인 실패
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserLoginFailureHandler(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println(" <<< UserLoginFailureHandler - onAuthenticationFailure 진입 >>>");
		
		String strId = request.getParameter("mem_id");
		String strPwd = request.getParameter("mem_pwd");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		int cnt = sqlSession.selectOne("com.security.test3.dao.MainDao.idPwdCheck", map);
		if(cnt!=1) {
			request.setAttribute("errorMsg", "로그인 정보가 일치하지 않습니다.");
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		dispatcher.forward(request, response);
	}

}
