package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 화면을 띄우기 전에 notice테이블로부터 들어있는 값을 뽑아서 응답페이지에 전달한다 <= 생각생각
		
		// 4) Service단에 SELECT요청
		// 공지사항 테이블에 있는 전체 행 => 가져올 수 있는 행의 갯수 : 최소 0개 ~ 여러개 => ArrayList
		ArrayList<Notice> list = new NoticeService().selectNoticeList();
		
		// 5) 뽑아온 list를 어디다 좀 담아서 응답페이지로 보내버리고 싶음
		request.setAttribute("list", list);
		
		// 화면띄우기
		request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
