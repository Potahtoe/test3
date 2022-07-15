package com.security.test3.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserVO extends User{
	
	// DTO(Data Transfer Object): 계층간 데이터 전달/ 필드값이 같아도 같은 객체가 아님/ setter 존재 시 가변, 비존재 시 불가변/ getter/setter외의 로직이 필요하지 않음
	// VO(Value Object): 값 자체 표현/ 필드값이 같으면 같은 객체/ 불변/ getter/setter 외의 로직이 있어도 무방함
	public UserVO(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
