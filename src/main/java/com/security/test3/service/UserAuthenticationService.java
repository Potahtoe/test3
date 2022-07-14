package com.security.test3.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public UserDetails loadUserByUsername(String mem_id) throws UsernameNotFoundException {
		logger.info("<<<UserAuthenticationService - loadUserByUsername 진입 >>>");
		MemberDto dto = sqlSession.selectOne("com.security.test3.dao.MainDao.selectId", mem_id);
		
		//로그인 인증 실패 시 인위적으로 예외 생성해서 던진다.
		if(dto ==null) throw new UsernameNotFoundException(mem_id);
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add(new SimpleGrantedAuthority(dto.getAuthority()));
		
		//UserVO 클래스 먼저 생성 후 return 
		return new UserVO(dto.getMem_id(), dto.getMem_pwd(), dto.getEnabled().equals("1"), true, true, true, authority);
	}
}
