package com.codehows.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberTests {
	
	//pwencoder �ʵ� : PasswordEncoder �������̽��� ������ ��ü�� ���� �޴´�.
	//�� ��ü�� ȸ�� ����� ��ȣȭ �ϰ�, ���߿� ����� Ȯ���� �� ���
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;
	
	//ds �ʵ� : �����ͺ��̽� ���� ������ ���� �޴� DataSource ��ü
	@Setter(onMethod_ = @Autowired)
	private DataSource ds;
	
	//tbl_member
	/*@Test
	public void testInsertMember() {
		
		//ȸ�������� userid, userpw, usename ������ �ʵ�� ������
		String sql = "insert into tbl_member(userid, userpw, username) values(?,?,?)";
		
		//0���� 99���� �ݺ�(�� 100���� ȸ���� �����ͺ��̽��� ����)
		for(int i=0; i<100; i++) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			//try-catch-finally ������ ����Ͽ� ���� ó���� ���ҽ� ������ �Ѵ�.
			//db���� �� ���� ����
			try {
				//DB���� ������ ���´�
				con = ds.getConnection();
				//SQL ���� �غ�
				pstmt = con.prepareStatement(sql);
				
				//����� ��ȣȭ�ϰ� DB�� ����, ����� 'pw'�� ���ڰ� ���� ���·� �� ȸ������ �ٸ��� ������
				pstmt.setString(2, pwencoder.encode("pw" + i));
				
				//i�� ���� ���� ȸ���� ������ �Ϲݻ��, ���, �����ڷ� �����ȴ�.
				//1���� 79����
				if(i<80) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "�Ϲݻ����"+i);
				} else if (i<90) {			//80���� 89����
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "���"+i);
				} else {		//90���� 99����
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "������"+i);
				}
				//SQL ������ �����ϰ� DB�� ��������� �����Ѵ�.
				pstmt.executeUpdate();
			} catch (Exception e) {
				//���ܰ� �߻��ϸ� e.printStackTrace ȣ���Ͽ� ���� ���
				e.printStackTrace();	
			} finally {		//finally������ PreparedStatemnet�� Connection ��ü�� �ݾ� ���ҽ� �����Ѵ�.
				if(pstmt != null) { try {pstmt.close();} catch(Exception e) {}}
				if(con != null) { try {con.close();} catch(Exception e) {}}
			}
		}//end for
	}	*/
	
	//tbl_member_auth (���� �ο�)
	@Test
	public void testInsertAuth() {
		String sql = "insert into tbl_member_auth (userid, auth) values(?,?)";
		
		for(int i=0; i<100; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				if(i<80) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(2, "ROLE_USER");
				} else if (i<90) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(2, "ROLE_MEMBER");
				} else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(2, "ROLE_ADMIN");
				}
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(pstmt != null) {try {pstmt.close();} catch(Exception e) {}}
				if(con != null) {try {con.close();} catch(Exception e) {}}
			}
		}//end for
	}
}
