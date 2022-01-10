<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list"); // : Object
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	    .outer{
        background-color: skyblue;
        color: white;
        width: 1000px;
		height: 500px;
        margin : auto;
        margin-top : 50px;
    }
	.list-area{
		border : 1px solid white;
		text-align: center;
	}
	.list-area>tbody>tr:hover{
		cursor : pointer;
		background : lightblue;
	}
	
	

</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>
	
	<div class="outer">

		<br>
		<h2 align="center">공지사항</h2>
		<br>

		<!-- 관리자가 로그인했을 시 글작성 버튼이 보이도록 함 -->
		<% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
		<!-- 로그인이 되었고 관리자일 경우 -->
			<div align="right" style="width:850px;">
				<!-- 
				<button onclick="location.href='이동할페이지'">글작성</button>
				버튼에 href속성이 없기 때문에
				버튼을 눌러서 페이지를 이동시키려면 onclick="location.href='요청url'"
				onclick속성에 직접적으로 자바스크립트 코드를 이용해서 요청해야한다.
				 -->
				 <!--  a태그를 쓰고도 버튼모양으로 만들고 싶다면? : 부트스트랩 활용 -->
				<a href="<%= contextPath %>/enrollForm.no" class="btn btn-sm btn-primary">글작성</a>
				
				<br><br>
			</div>
		<%} %>

	
		<table align="center" class="list-area">
			<thead>
				<tr>
					<th>글번호</th>
					<th width="400">글제목</th>
					<th width="100">작성자</th>
					<th>조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				<!-- 공지사항이 하나도 존재하지 않을경우 -->
				<!-- 리스트가 비어있을경우 -->
				<%if(list.isEmpty()) { %>
					<tr>
						<td colspan="5">공지사항이 없습니다.</td>
					</tr>
				<% }else {%> <!-- 리스트가 차있을 경우 -->
					<!-- 공지사항이 존재할 경우 -->
						<!-- 향상된 for문! -->
						<% for(Notice n : list) { %>
							<tr>
								<td><%= n.getNoticeNo() %></td>
								<td><%= n.getNoticeTitle() %></td>
								<td><%= n.getNoticeWriter() %></td>
								<td><%= n.getCount() %></td>
								<td><%= n.getCreateDate() %></td>
							</tr>
						<% } %>
				<% } %>
			</tbody>
		</table>

		<script>
			$(function(){
				$(".list-area>tbody>tr").click(function(){


					// 클릭했을 때 해당 공지사항의 번호를 넘기자!!!!
					// 해당 tr태그의 자손 중에서도 첫번째 td의 값이 필요함!!(내용물을 뽑자!!!)
					var nno = $(this).children().eq(0).text(); //글번호

					console.log(nno);

					// 글번호가지고 요청
					// 대놓고 요청 => url에 키와 밸류를 대놓고 작성해서 요청을 보내버리자
					// GET방식 : 요청할url?키=밸류&키=밸류&키=밸류....
					// "쿼리스트링" => ? 뒤의 내용들, 직접만들어서 보내보자
					// localhost:8001/jsp/detail.no?nno=글번호

					location.href = "<%= contextPath %>/detail.no?nno=" + nno;
				})
			})
		</script>


	
	</div>
</body>
</html>