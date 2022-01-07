package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) request객체로부터 요청 시 전달값을 get하기
		String userId = request.getParameter("userId"); // 필수입력사항
		String userPwd = request.getParameter("userPwd"); // 필수입력사항
		String userName = request.getParameter("userName"); // 필수입력사항
		String phone = request.getParameter("phone"); // 빈 문자열이 들어갈 수 있음
		String email = request.getParameter("email"); // 빈 문자열이 들어갈 수 있음
		String address = request.getParameter("address"); // 빈 문자열이 들어갈 수 있음
		String[] interests = request.getParameterValues("interest"); // null or ["운동", "등산", ...]
		
		String interest = "";
		
		if(interests != null) {
			interest = String.join(",", interests);
		}
		
		// 3) 매개변수 생성자를 이용해서 Memebr객체에 담기
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 4) 요청처리(Service단으로 토스)
		int result = new MemberService().insertMember(m);
		
		// 5) 처리결과를 가지고 사용자가 보게 될 응답화면 지정
		if(result >  0) { // 성공? jsp요청 -> url 재요청방식(sendRedirect)
			request.getSession().setAttribute("alertMsg", "회원가입 성공!");
			response.sendRedirect(request.getContextPath());
		} else { // 실패? 에러페이지
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);
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
