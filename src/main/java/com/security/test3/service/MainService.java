package com.security.test3.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface MainService {
	
	//----------회원가입---------
	//회원가입 처리
	public void signInAction(HttpServletRequest req, Model model);
	//권한 업데이트
	public void updateGrade(HttpServletRequest req, Model model);
	
	//loginAction은 security-context.xml에 설정해둔 login_check.do가 대신 처리하므로 관련 메서드 삭제
	
	//----------게시판---------
	//게시판 목록 조회
	public void boardList(HttpServletRequest req, Model model);
	//게시판 등록 처리
	public void boardInsertAction(HttpServletRequest req, Model model);
	//게시판 상세 조회
	public void boardDetail(HttpServletRequest req, Model model);
	//게시판 수정 화면
	public void boardUpdate(HttpServletRequest req, Model model);
	//게시판 수정 처리
	public void boardUpdateAction(HttpServletRequest req, Model model);
	//게시판 삭제 처리
	public void boardDeleteAction(HttpServletRequest req, Model model);
	//게시판 검색
	public void boardSearch(HttpServletRequest req, Model model);
}
