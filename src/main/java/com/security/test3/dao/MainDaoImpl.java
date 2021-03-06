package com.security.test3.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.test3.dto.BoardDto;
import com.security.test3.dto.MemberDto;

@Repository
public class MainDaoImpl implements MainDao{
	private static final Logger logger = LoggerFactory.getLogger(MainDaoImpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	//-----------시큐리티-------------
	//아이디 체크
	@Override
	public int idCheck(String strId) {
		logger.info("dao - 아이디 체크");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.idCheck(strId);
	}
	//비밀번호 체크
	@Override
	public String pwdCheck(String strId) {
		logger.info("dao - 비밀번호 체크");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.pwdCheck(strId);
	}
	//로그인 체크(db에 있는 아이디 불러오기)
	@Override
	public MemberDto selectId(String strId) {
		logger.info("dao - 로그인 체크");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.selectId(strId);
	}
	//권한 불러오기
	@Override
	public String authorityCheck() {
		logger.info("dao - 권한 불러오기");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.authorityCheck();
	}
	//권한 업데이트
	@Override
	public int updateGrade(String mem_id) {
		logger.info("dao - 권한 업데이트");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.updateGrade(mem_id);
	}
	
	//-----------회원가입-------------
	//회원가입 처리
	@Override
	public int signInAction(MemberDto dto) {
		logger.info("dao - 회원가입 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.signInAction(dto);
	}

	//기존의 idPwdCheck는 비밀번호 암호화로 인해 idCheck와 pwdCheck로 분리하여 사용
	
	//-----------게시글-------------
	//게시글 수
	@Override
	public int boardCnt() {
		logger.info("dao - 게시판 게시글 수");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardCnt();
	}
	
	//게시판 목록 조회
	@Override
	public List<BoardDto> boardList(Map<String,Object> map) {
		logger.info("dao - 게시판 목록 조회");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		List<BoardDto> list = dao.boardList(map);
		return list;
	}

	//게시판 등록 처리
	@Override
	public int boardInsertAction(BoardDto dto) {
		logger.info("dao - 게시판 등록 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardInsertAction(dto);
	}

	//조회수 증가
	@Override
	public void plusReadCnt(int board_no) {
		logger.info("dao - 조회수 증가");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		dao.plusReadCnt(board_no);
	}

	//게시판 상세 조회
	@Override
	public BoardDto boardDetail(int board_no) {
		logger.info("dao - 게시판 상세 조회");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardDetail(board_no);
	}

	//게시판 수정 화면
	@Override
	public BoardDto boardUpdate(int board_no) {
		logger.info("dao - 게시판 수정 화면");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardUpdate(board_no);
	}
	
	//게시판 수정 처리
	@Override
	public int boardUpdateAction(BoardDto dto) {
		logger.info("dao - 게시판 수정 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardUpdateAction(dto);
	}

	//게시판 삭제 처리
	@Override
	public int boardDeleteAction(int board_no) {
		logger.info("dao - 게시판 삭제 처리");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardDeleteAction(board_no);
	}

	 //검색글 수
	@Override 
	public int searchCnt(String searchContent) {
		logger.info("dao - 게시판 검색글 수");
	
		MainDao dao = sqlSession.getMapper(MainDao.class); 
		return dao.searchCnt(searchContent); 
	}
	
	//게시판 검색
	@Override
	public List<BoardDto> boardSearch(Map<String,Object> map) {
		logger.info("dao - 게시판 검색");
		
		MainDao dao = sqlSession.getMapper(MainDao.class);
		return dao.boardSearch(map);
	}
}
