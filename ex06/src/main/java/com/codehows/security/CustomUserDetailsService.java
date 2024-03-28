package com.codehows.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.codehows.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//UserDetailsService 인터페이스를 구현한 클래스
@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	//MemberMapper 인터페이스를 구현 객체 memberMapper 주입 받는다
	//객체 memberMapper는 사용자 정보를 데이터베이스에서 조회하기 위해서 사용된다.
	@Setter(onMethod_ = {@Autowired})
	private MemberMapper memberMapper;
	
	
	//loadUserByUsername메소드는 spring security에서 사용자이름(아이디)를 받아서 해당 사용자 정보를 조회하고
	//UserDetails 객체를 반환한다
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		//사용자 이름을 포함하여 출력되고, 사용자 정보를 가져오는 작업이 수행된다.
		log.warn("Load User By UserName : " + userName);
		
		//현재 구현은 사용자 정보를 반환하지 않고 null을 반환한다.
		return null;
	}

}
