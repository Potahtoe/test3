package com.security.test3.dto;

public class MemberDto {
	private String mem_id;
	private String mem_pwd;
	private String user_role;
	
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

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	@Override
	public String toString() {
		return "MemberDto [mem_id=" + mem_id + ", mem_pwd=" + mem_pwd + ", user_role=" + user_role + "]";
	}
}
