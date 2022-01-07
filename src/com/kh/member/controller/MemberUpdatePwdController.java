package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) POST방식이므로 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) request로부터 값 찾아오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		// 3) VO 객체에 담아서 가공 -> 패스~
		
		// 4) Service 단으로 토스
		Member updateMember = new MemberService().updatePwdMember(userId, userPwd, updatePwd);
		
		HttpSession session = request.getSession();
		
		if(updateMember == null) { // 실패했을 경우
			session.setAttribute("alertMsg", "비밀번호 수정에 실패했습니다.");
		} else {
			session.setAttribute("alertMsg", "비밀번호가 성공적으로 변경되었습니다.");
		}
		
		// 성공, 실패 둘 다 마이페이지로 보내기
		response.sendRedirect(request.getContextPath() + "/mypage.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
