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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) POST방식 => 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) request객체로부터 요청 시 전달값을 get하기
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest");
		
		String interest = "";
		if(interests != null) {
			interest = String.join(",", interests);
		}
		
		// 3) VO객체에 담기
		Member m = new Member(userId, userName, phone, email, address, interest);
		
		// 4) Service단으로 토스
		Member updateMember = new MemberService().updateMember(m);
		
		if(updateMember == null) { // 실패했을 경우
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		} else { // 성공했을 경우 => 마이페이지 화면 but 변경된 정보 보여주기
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", updateMember);
			session.setAttribute("alertMsg", "성공적으로 회원정보가 수정되었습니다.");
			
			// 마이페이지에서 정보변경 후 sendRedirect로 넘겨주기
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
