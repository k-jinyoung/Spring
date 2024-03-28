package com.codehows.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
// �� Ŭ������ ��й�ȣ�� ���ڵ��ϰ� ��ġ ���θ� Ȯ���ϴ� ������ ����� �����Ѵ�.
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
	
	//encode()�޼ҵ�� �־��� ���� ��й�ȣ(rawpassword)�� �޾ƿ� �� �״�� ��ȯ
	//����� �������� �ʰ� �״�� ��ȯ, �̰��� No-op(No Operation)�� ��Ÿ����.
	public String encode(CharSequence rawPassword) {
			
			log.warn("before encode :" +rawPassword);
			
			//�޼ҵ� �ȿ� �־��� ���� ����� �޾ƿͼ� toString()�޼ҵ带 ����ؼ� ���ڿ��� ��ȯ �� ��ȯ�Ѵ�.
			return rawPassword.toString();
		}
		
		//matches()�޼ҵ�� �־��� ���� ����� ���ڵ��� ���(encodedpw)�� ��ġ�ϴ��� Ȯ���Ѵ�.		
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			
			//�� �޼ҵ�� �־��� ���� ����� ���ڵ��� ����� �α׿� ��� ��
			log.warn("matches: " + rawPassword + ":" + encodedPassword);
			
			//�� ����� ������ ���Ͽ� ��ġ�ϸ� true ��ȯ, �ƴϸ� false ��ȯ
			return rawPassword.toString().equals(encodedPassword);
		}
	}


