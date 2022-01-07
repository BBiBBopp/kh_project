package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		// Service => Connection 객체
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) DAO에 요청
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		// 3) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 4) Controller로 결과 넘기기
		return m;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMemberDao(conn, m);
		
		// 성공했으면 1, 실패했다면 0으로 리턴
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else { // 실패했다면
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public Member updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMemberDao(conn, m);
		
		// 업데이트를 성공한 경우 갱신된 회원객체 다시 조회해오기
		Member updateMember = null;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			updateMember = new MemberDao().selectMember(conn, m.getUserId());
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateMember;
		
	}

	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		Connection conn = JDBCTemplate.getConnection();
		
		// 비밀번호를 업데이트하는 dao메소드 호출
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		
		Member updateMember = null;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			// 갱신된 회원 객체 다시 받아오기
			updateMember = new MemberDao().selectMember(conn, userId);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return updateMember;
		
	}

	public int deleteMember(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		
		// 회원을 탈퇴처리하는 dao메소드 호출(삭제 아님, STATUS -> N으로!!)
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}

}
