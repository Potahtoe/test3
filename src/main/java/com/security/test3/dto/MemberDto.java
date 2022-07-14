package com.security.test3.dto;

public class MemberDto {
	private String mem_id; //아이디
	private String mem_pwd; //비밀번호
	private String authority; // ROLE_ADMIN / ROLE_USER
	private String enabled; // 1 / 0
	
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "MemberDto [mem_id=" + mem_id + ", mem_pwd=" + mem_pwd + ", authority=" + authority + ", enabled="
				+ enabled + "]";
	}
}
