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
		String userPwd = request.getParameter("userPwd");
		
		// 현재 로그인한 회원의 정보!!
		// 방법 1. input type="hidden"으로 애초에 숨겨서 전달을 해버린다.
		// 방법 2. session영역에 담겨있는 회원 객체를 뽑기
		
		// 세션에 담겨있는 기존의 로그인된 사용자의 정보를 얻어온다.
		HttpSession session = request.getSession();
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		
		// 3) service단으로 토스
		int result = new MemberService().deleteMember(userId, userPwd);
		// 5) 결과에 따른 응답페이지로 지정
		if(result > 0) { // 성공  => 로그인이 유지되어야 하나요? NO => session.invalidate()
			// alert띄워주기 => session에 키 "alertMsg"로 넘기는 코드를 활용할건데
			// session.removeAttribute()
					
			// invalidate()메소드를 사용하면 세션이 만료되어 alert이 작동되지 않을것
			// removeAttribute()를 활용해서 로그아웃을 시켜보자
			session.removeAttribute("loginUser");
					
			// 로그아웃이 되었으므로 마이페이지가 보이면 안됨
			// 메인페이지로 보내버리자!! => localhost:8001/jsp
			response.sendRedirect(request.getContextPath());		
		} else { // 실패 => 탈퇴가 아직 안된것 => 오류페이지로 보내버리기 => 아예 경로를 지정해서 포워딩	
			
			request.setAttribute("errorMsg", "회원 탈퇴에 실패했습니다");	
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
