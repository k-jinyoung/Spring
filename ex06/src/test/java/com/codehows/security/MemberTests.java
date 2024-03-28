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
	
	//pwencoder 필드 : PasswordEncoder 인터페이스를 구현한 객체를 주입 받는다.
	//이 객체는 회원 비번을 암호화 하고, 나중에 비번을 확인할 때 사용
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwencoder;
	
	//ds 필드 : 데이터베이스 연결 정보를 주입 받는 DataSource 객체
	@Setter(onMethod_ = @Autowired)
	private DataSource ds;
	
	//tbl_member
	/*@Test
	public void testInsertMember() {
		
		//회원정보는 userid, userpw, usename 세가지 필드로 구성됨
		String sql = "insert into tbl_member(userid, userpw, username) values(?,?,?)";
		
		//0부터 99까지 반복(총 100명의 회원을 데이터베이스에 삽입)
		for(int i=0; i<100; i++) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			//try-catch-finally 구문을 사용하여 예외 처리와 리소스 해제를 한다.
			//db연결 및 쿼리 실행
			try {
				//DB로의 연결을 얻어온다
				con = ds.getConnection();
				//SQL 쿼리 준비
				pstmt = con.prepareStatement(sql);
				
				//비번을 암호화하고 DB에 저장, 비번은 'pw'에 숫자가 붇은 형태로 각 회원마다 다르게 생성됨
				pstmt.setString(2, pwencoder.encode("pw" + i));
				
				//i의 값에 따라 회원의 역할이 일반사용, 운영자, 관리자로 지정된다.
				//1부터 79까지
				if(i<80) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "일반사용자"+i);
				} else if (i<90) {			//80부터 89까지
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "운영자"+i);
				} else {		//90부터 99까지
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "관리자"+i);
				}
				//SQL 쿼리를 실행하고 DB에 변경사항을 적용한다.
				pstmt.executeUpdate();
			} catch (Exception e) {
				//예외가 발생하면 e.printStackTrace 호출하여 예외 출력
				e.printStackTrace();	
			} finally {		//finally에서는 PreparedStatemnet와 Connection 객체를 닫아 리소스 해제한다.
				if(pstmt != null) { try {pstmt.close();} catch(Exception e) {}}
				if(con != null) { try {con.close();} catch(Exception e) {}}
			}
		}//end for
	}	*/
	
	//tbl_member_auth (권한 부여)
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
