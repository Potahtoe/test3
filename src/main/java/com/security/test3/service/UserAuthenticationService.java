package com.security.test3.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.test3.dto.MemberDto;
import com.security.test3.dto.UserVO;

//로그인 시 인증
public class UserAuthenticationService implements UserDetailsService{

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public UserDetails loadUserByUsername(String strId) throws UsernameNotFoundException {
		System.out.println("<<<UserAuthenticationService - loadUserByUsername 진입 >>>");
		MemberDto dto = sqlSession.selectOne("com.security.test3.dao.MainDao.selectId", strId);
		System.out.println("로그인 체크 ==> " + dto);
		
		//로그인 인증 실패 시 인위적으로 예외 생성해서 던진다.
		if(dto ==null) throw new UsernameNotFoundException(strId);
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add(new SimpleGrantedAuthority(dto.getAuthority())); //default 'ROLE_USER'
		
		//UserVO 클래스 먼저 생성 후 return
		//시큐리티 로그인에서 체크 : id, password, authority(ROLE_USER/ ROLE_ADMIN), enabled(이메일 인증 시 "1"로 update 치며, 이메일 인증 후 시큐리티 적용) 
		return new UserVO(dto.getMem_id(), dto.getMem_pwd(), dto.getEnabled().equals("1"), true, true, true, authority);
	}

}
