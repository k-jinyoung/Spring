package com.codehows.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.codehows.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//UserDetailsService �������̽��� ������ Ŭ����
@Log4j
public class CustomUserDetailsService implements UserDetailsService {
	
	//MemberMapper �������̽��� ���� ��ü memberMapper ���� �޴´�
	//��ü memberMapper�� ����� ������ �����ͺ��̽����� ��ȸ�ϱ� ���ؼ� ���ȴ�.
	@Setter(onMethod_ = {@Autowired})
	private MemberMapper memberMapper;
	
	
	//loadUserByUsername�޼ҵ�� spring security���� ������̸�(���̵�)�� �޾Ƽ� �ش� ����� ������ ��ȸ�ϰ�
	//UserDetails ��ü�� ��ȯ�Ѵ�
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		//����� �̸��� �����Ͽ� ��µǰ�, ����� ������ �������� �۾��� ����ȴ�.
		log.warn("Load User By UserName : " + userName);
		
		//���� ������ ����� ������ ��ȯ���� �ʰ� null�� ��ȯ�Ѵ�.
		return null;
	}

}
