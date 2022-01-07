package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class DeleteMemberController
 */
@WebServlet("/deleteMember.me")
public class DeleteMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) POST방식 => 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) request객체로부터 전달값 get
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) service단으로 토스
		int result = new MemberService().deleteMember(userId, userPwd);
		if(result > 0) { // 성공 -> 세션 만료, 메인페이지로 보내기
			// 세션 만료시키기
			request.getSession().invalidate();
			request.getSession().setAttribute("alertMsg", "회원탈퇴 성공");
			// 메인으로 보내기
			response.sendRedirect(request.getContextPath());
		} else {
			// 회원탈퇴 실패하면 마이페이지
			request.setAttribute("errorMsg", "회원 탈퇴에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/mypage.me");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
