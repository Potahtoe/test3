package com.security.test3.dto;

public class MemberDto {
	private String mem_id;
	private String mem_pwd;
	
	public MemberDto() {}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pwd() {
		return mem_pwd;
	}

	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	@Override
	public String toString() {
		return "MemberDto [mem_id=" + mem_id + ", mem_pwd=" + mem_pwd + "]";
	}
}
