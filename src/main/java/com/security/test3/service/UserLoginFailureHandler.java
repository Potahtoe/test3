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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

//로그인 실패
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserLoginFailureHandler.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder; //비밀번호 암호화 클래스
	
	public UserLoginFailureHandler(SqlSessionTemplate sqlSession, BCryptPasswordEncoder passwordEncoder) {
		this.sqlSession=sqlSession;
		this.passwordEncoder=passwordEncoder;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info(" <<< UserLoginFailureHandler - onAuthenticationFailure 진입 >>>");
		
		String strId = request.getParameter("mem_id");
		String strPwd = request.getParameter("mem_pwd");
		
		int cnt = sqlSession.selectOne("com.security.test3.dao.MainDao.idCheck", strId);
		if(cnt!=0) {
			//암호화된 비밀번호 가져오기
			String encryptPassword = sqlSession.selectOne("com.security.test3.dao.MainDao.pwdCheck", strId);
			logger.info("암호화 된 비밀번호 : " + encryptPassword);
			logger.info("화면에서 입력받은 비밀번호 : " + strPwd);
			
			//입력한 비밀번호와 가입된 비밀번호(암호화된 비밀번호)가 일치하는지 여부
			if(passwordEncoder.matches(strPwd, encryptPassword)) {
				request.setAttribute("errorMsg", "접근 권한이 없습니다.");
			}else {
				logger.info("<<< 비밀번호 불일치 >>>");
				request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
			}
			
		}else {
			logger.info("아이디 불일치");
			request.setAttribute("errorMsg", "아이디가 일치하지 않습니다.");
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		dispatcher.forward(request, response);
	}

}
