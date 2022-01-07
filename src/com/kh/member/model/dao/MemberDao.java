package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Member loginMember(Connection conn, String userId, String userPwd){
		
		// SELECT문 => ResultSet객체(unique key조건에 의해 한 행만 조회됨) => Member객체에 담아서 반환
		
		// 필요한 변수
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			// pstmt객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 구멍 메꾸기
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			// 쿼리문 실행 후 결과 받기
			// 쿼리문 실행 메소드
			// pstmt.executeQuery(); => SELECT
			// pstme.executeUpdate(); => INSERT/UPDATE/DELETE
			rset = pstmt.executeQuery();
			
			// rset으로부터 각각의 컬럼값으로 뽑아서 Member객체에 담는다.
			// rset.next()
			// 조회 결과가 여러행일 때 => while(rset.next())
			// 조회 결과가 한행일 때 => if(rset.next())
			if(rset.next()) {
				// 각 컬럼의 값을 뽑기
				// rset.getInt / getString / getDate(뽑아올 컬럼명 또는 컬럼의 순번)
				
				m = new Member(rset.getInt("USER_NO")
								,rset.getString("USER_ID")
								,rset.getString("USER_PWD")
								,rset.getString("USER_NAME")
								,rset.getString("PHONE")
								,rset.getString("EMAIL")
								,rset.getString("ADDRESS")
								,rset.getString("INTEREST")
								,rset.getDate("ENROLL_DATE")
								,rset.getDate("MODIFY_DATE")
								,rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 => 생성된 순서의 역순
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
		
	}

	public int insertMemberDao(Connection conn, Member m) {
		
		// INSERT문 => 처리된 행의 갯수
		
		// 필요한 변수 생성
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			// pstmt객체 생성(sql 미리 넘기기)
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더 채우기
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			// SQL 실행 및 결과 받아오기
			// select => pstmt.executeQuery();
			// insert/update/delete => pstmt.executeUpdate();
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(pstmt);
		}
		
		// 결과 리턴
		return result;
		
	}

	public int updateMemberDao(Connection conn, Member m) {
		// UPDATE => 처리된 행의 갯수 반환
		
		// 필요한 변수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			// pstmt객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더 채우기
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			// sql 실행결과 받아오기
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection conn, String userId) {
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(
							rset.getInt("USER_NO"),
							rset.getString("USER_ID"),
							rset.getString("USER_PWD"),
							rset.getString("USER_NAME"),
							rset.getString("PHONE"),
							rset.getString("EMAIL"),
							rset.getString("ADDRESS"),
							rset.getString("INTEREST"),
							rset.getDate("ENROLL_DATE"),
							rset.getDate("MODIFY_DATE"),
							rset.getString("STATUS")
					);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
	}

	public int updatePwdMember(Connection conn, String userId, String userPwd, String updatePwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userPwd);
			pstmt.setString(3, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int deleteMember(Connection conn, String userId, String userPwd) {
		// insert, update, delete 는 처리 행 수 반환
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
