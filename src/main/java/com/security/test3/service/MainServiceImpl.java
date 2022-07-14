package com.security.test3.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.security.test3.dao.MainDao;
import com.security.test3.dto.BoardDto;
import com.security.test3.dto.MemberDto;
import com.security.test3.page.Paging;

@Service
public class MainServiceImpl implements MainService{
	private static final Logger logger = LoggerFactory.getLogger(MainServiceImpl.class);
	
	@Autowired
	MainDao dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder; //비밀번호 암호화 클래스
	
	//---------------회원가입--------------------
	//회원가입 처리 (시큐리티 적용 후 수정)
	@Override
	public void signInAction(HttpServletRequest req, Model model) {
		logger.info("서비스 - 회원가입 처리");
		
		//dto 바구니 생성
		MemberDto dto = new MemberDto();
		
		//jsp에서 넘긴 id와 pwd 값을 dto에 담는다
		dto.setMem_id(req.getParameter("mem_id"));
		String mem_pwd = req.getParameter("mem_pwd");
		String encryptPassword =passwordEncoder.encode(mem_pwd);
		dto.setMem_pwd(encryptPassword);
		//enabled값
		String enabled = req.getParameter("enabled");
		dto.setEnabled(enabled);

		//dao를 통해 db에 저장
		int insertCnt = dao.signInAction(dto);
		logger.info("insertCnt : " + insertCnt);
		
		//jsp로 결과 보내주기(signInAction.jsp)
		model.addAttribute("insertCnt", insertCnt);
		
		
	}
	
	//권한 업데이트
	@Override
	public void updateGrade(HttpServletRequest req, Model model) {
		logger.info("서비스 - 권한 업데이트");
		
		String enabled = req.getParameter("enabled");
		String mem_id = req.getParameter("mem_id");
		
		if(enabled.equals("1")) {
			logger.info("enabled : " + enabled.equals("1"));
			int updateCnt = dao.updateGrade(mem_id);
			model.addAttribute("updateCnt", updateCnt);
		}
	}
	
	//loginAction은 security-context.xml에 설정해둔 login_check.do가 대신 처리하므로 관련 메서드 삭제
	
	//---------------게시판--------------------
	//게시판 목록 조회
	@Override
	public void boardList(HttpServletRequest req, Model model) {
		logger.info("서비스 - 게시판 목록 조회");
		
		String pageNum = req.getParameter("pageNum");
		Paging paging = new Paging(pageNum);
		int total = dao.boardCnt();
		
		paging.setTotalCount(total);
		
		int start = paging.getStartRow()-1;
		int end = paging.getEndRow();
		
		List<BoardDto> list = null;
		if(total>0) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("start", start);
			map.put("end", end);
			list=dao.boardList(map);
		}
				
		//list를 jsp로 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("total", total);
	}

	//게시판 등록 처리
	@Override
	public void boardInsertAction(HttpServletRequest req, Model model) {
		logger.info("서비스 - 게시판 등록 처리");
		
		//dto 바구니 생성
		BoardDto dto = new BoardDto();
		
		//화면에서 입력받은 값을 dto에 담기
		dto.setBoard_title(req.getParameter("board_title"));
		dto.setBoard_writer(req.getParameter("board_writer"));
		dto.setBoard_contents(req.getParameter("board_contents"));
		
		//dao를 통해 db에 저장
		int insertCnt = dao.boardInsertAction(dto);
		logger.info("insertCnt : " + insertCnt);
		
		//jsp로 처리 결과 전달
		model.addAttribute("insertCnt", insertCnt);
	}

	//게시판 상세 조회
	@Override
	public void boardDetail(HttpServletRequest req, Model model) {
		logger.info("서비스 - 게시판 상세 조회");
		
		//화면에서 게시글 번호 받아오기
		String pageNum = req.getParameter("crtPage");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		String searchContent = req.getParameter("searchContent");
		
		//상세 클릭할 때마다 조회수 증가
		dao.plusReadCnt(board_no);
		
		//받아온 게시글 번호를 통해 db에 저장된 값을 불러온다
		BoardDto dto = dao.boardDetail(board_no);
		
		//불러온 값을 jsp에 전달한다
		model.addAttribute("dto", dto);
		model.addAttribute("crtPage", pageNum);
		model.addAttribute("searchContent", searchContent);
	}

	//게시판 수정 화면
	@Override
	public void boardUpdate(HttpServletRequest req, Model model) {
		logger.info("서비스 - 게시판 수정 화면");
		
		//화면에서 게시글 번호 받아오기
		String pageNum = req.getParameter("crtPage");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		String searchContent = req.getParameter("searchContent");
		
		//받아온 게시글 번호를 통해 db에 저장된 값을 불러온다
		BoardDto dto = dao.boardUpdate(board_no);
		
		//불러온 값을 jsp에 전달한다
		model.addAttribute("dto", dto);
		model.addAttribute("crtPage", pageNum);
		model.addAttribute("searchContent", searchContent);
	}
	
	//게시판 수정 처리
	@Override
	public void boardUpdateAction(HttpServletRequest req, Model model) {
		logger.info("서비스 - 게시판 수정 처리");
		
		//dto 주머니 생성
		BoardDto dto = new BoardDto();
		
		//화면에 입력된 값 불러와서 dto에 담기
		String pageNum = req.getParameter("crtPage");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		String searchContent = req.getParameter("searchContent");
		
		dto.setBoard_no(Integer.parseInt(req.getParameter("board_no")));
		dto.setBoard_title(req.getParameter("board_title"));
		dto.setBoard_contents(req.getParameter("board_contents"));
		
		//dao를 통해 db에 저장
		int updateCnt = dao.boardUpdateAction(dto);
		
		//jsp로 결과 전달
		model.addAttribute("updateCnt", updateCnt);
		model.addAttribute("crtPage", pageNum);
		model.addAttribute("board_no", board_no);
		model.addAttribute("searchContent", searchContent);
	}

	//게시판 삭제 처리
	@Override
	public void boardDeleteAction(HttpServletRequest req, Model model) {
		logger.info("서비스 - 게시판 삭제 처리");
		
		//화면에서 게시글 번호 받아오기
		String pageNum = req.getParameter("crtPage");
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		String searchContent = req.getParameter("searchContent");
		
		//받아온 게시글 번호를 통해 db에 보낸다
		int deleteCnt = dao.boardDeleteAction(board_no);
		logger.info("삭제 deleteCnt : " + deleteCnt);
		
		//불러온 값을 jsp에 전달한다
		model.addAttribute("deleteCnt", deleteCnt);
		model.addAttribute("crtPage", pageNum);
		model.addAttribute("searchContent", searchContent);
	}
	//게시판 검색
	@Override
	public void boardSearch(HttpServletRequest req, Model model) {
		logger.info("서비스 - 게시판 검색");
		
		//화면에서 검색어 입력 받아오기
		String pageNum = req.getParameter("pageNum");
		String searchContent = req.getParameter("searchContent");
		
		Paging paging = new Paging(pageNum);
		int total = dao.searchCnt(searchContent);
		paging.setTotalCount(total);
		int start = paging.getStartRow()-1;
		
		//dao를 통해 검색값을 list에 담기
		List<BoardDto> list = null; 
		if(total>0) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("start", start);
			map.put("searchContent", searchContent);
			list=dao.boardSearch(map);
		}
		logger.info("list : " + list);
		
		//결과값 jsp에 전달
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("total", total);
		model.addAttribute("searchContent", searchContent);
	}


}