package com.security.test3.dto;

import java.sql.Date;

public class BoardDto {
	private int num;//게시글번호
	private int board_no; //게시글번호
	private String board_title; //제목
	private String board_contents; //내용
	private String board_writer; //작성자
	private int read_cnt; //조회수
	private Date in_date; //작성일
	
	public BoardDto() {}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_contents() {
		return board_contents;
	}

	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public int getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	@Override
	public String toString() {
		return "BoardDto [num=" + num + ", board_no=" + board_no + ", board_title=" + board_title + ", board_contents="
				+ board_contents + ", board_writer=" + board_writer + ", read_cnt=" + read_cnt + ", in_date=" + in_date
				+ "]";
	}
	
	

}
