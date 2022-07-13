package com.security.test3.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.test3.dto.BoardDto;
import com.security.test3.dto.MemberDto;

@Repository
public class MainDaoImpl implements MainDao{

	@Autowired
	SqlSession sqlSession;
	
	//-----------시큐리티-------------
	//로그인 체크(db에 있는 아이디 불러오기)
	@Override
	public MemberDto selectId(String strId) {
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.selectId(strId);
	}
	//권한 불러오기
	@Override
	public String authorityCheck() {
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.authorityCheck();
	}
	//권한 업데이트
	@Override
	public int updateGrade(String enabled) {
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.updateGrade(enabled);
	}
	
	//-----------회원가입-------------
	//회원가입 처리
	@Override
	public int signInAction(MemberDto dto) {
		System.out.println("dao - 회원가입 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.signInAction(dto);
	}

	//-----------로그인-------------
	//로그인 확인
	@Override
	public int idPwdCheck(Map<String, Object> map) {
		System.out.println("dao - 로그인 확인");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.idPwdCheck(map);
	}
	
	//-----------게시글-------------
	//게시글 수
	@Override
	public int boardCnt() {
		System.out.println("dao - 게시판 게시글 수");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardCnt();
	}
	
	//게시판 목록 조회
	@Override
	public List<BoardDto> boardList(Map<String,Object> map) {
		System.out.println("dao - 게시판 목록 조회");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		List<BoardDto> list = dao.boardList(map);
		return list;
	}

	//게시판 등록 처리
	@Override
	public int boardInsertAction(BoardDto dto) {
		System.out.println("dao - 게시판 등록 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardInsertAction(dto);
	}

	//조회수 증가
	@Override
	public void plusReadCnt(int board_no) {
		System.out.println("dao - 조회수 증가");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		dao.plusReadCnt(board_no);
	}

	//게시판 상세 조회
	@Override
	public BoardDto boardDetail(int board_no) {
		System.out.println("dao - 게시판 상세 조회");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardDetail(board_no);
	}

	//게시판 수정 화면
	@Override
	public BoardDto boardUpdate(int board_no) {
		System.out.println("dao - 게시판 수정 화면");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardUpdate(board_no);
	}
	
	//게시판 수정 처리
	@Override
	public int boardUpdateAction(BoardDto dto) {
		System.out.println("dao - 게시판 수정 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardUpdateAction(dto);
	}

	//게시판 삭제 처리
	@Override
	public int boardDeleteAction(int board_no) {
		System.out.println("dao - 게시판 삭제 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardDeleteAction(board_no);
	}

	 //검색글 수
	@Override 
	public int searchCnt(String searchContent) {
		System.out.println("dao - 게시판 검색글 수");
	
		MainDao dao = sqlSession.getMapper(MainDao.class); 
		return dao.searchCnt(searchContent); 
	}
	
	//게시판 검색
	@Override
	public List<BoardDto> boardSearch(Map<String,Object> map) {
		System.out.println("dao - 게시판 검색");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardSearch(map);
	}




}
