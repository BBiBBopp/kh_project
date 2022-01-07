<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	
	String contextPath = request.getContextPath();
	
	Member loginUser = (Member)session.getAttribute("loginUser"); // : Object
	// 로그인 전 : menubar.jsp가 로딩될 때 null
	// 로그인 후 : munubar.jsp가 로딩될 때 로그인한 회원의 정보가 담겨있음
	
	String alertMsg = (String)session.getAttribute("alertMsg"); // : Object
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
   .nav-area{background-color: rgb(13,71,161);}
   .menu{
       display: table-cell; /* 인라인 요소처럼 배치 가능 */
       height: 50px;
       width: 150px;
   }
   .menu a{
       text-decoration: none;
       color: white;
       font-size: 20px;
       font-weight: bold;
       display: block;
       width: 100%;
       height: 100%;
       line-height: 50px;
   }
   .menu a:hover{background-color: rgb(25, 90, 189);}

   .login-area{float: right;}
</style>
</head>
<body>

	<script>
		var msg = "<%= alertMsg %>"; // "메시지 문구" / "null"
		
		if(msg != "null"){ // 즉, 성공 / 경고 메시지 문구가 담겨있을 경우
			alert(msg);
			// menubar.jsp가 로딩될 때마다 매번 alert가 떠버림
			// session에 들어있는 alertMsg 키값에 대한 밸류를 지워주면 됨
			// => XX.removeAttribute("키값")
			<% session.removeAttribute("alertMsg"); %>
			
		}
		
		
	</script>
	
	<h1 align="center">☆★ My first Web ☆★</h1>

    <div class="login-area">
    
    <% if(loginUser == null){ %>
    <!-- 로그인 전에 보여지는 로그인 form -->
        <form action="<%= contextPath %>/login.me" method="post">
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="userId" id="userId"></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" name="userPwd" id="userPwd"></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <button type="submit">로그인</button>
                        <button type="button" onclick="enrollPage();">회원가입</button>
                    </th>
                </tr>
            </table>
        </form>
        
        <script>
        	function enrollPage(){
        		//페이지 이동
        		// location.href = "<%= contextPath %>/views/member/memberEnrollForm.jsp"
        		// localhost:8001/jsp/views/member/memberEnrollForm.jsp
        		
        		// 단순한 정적인 페이지 요청이라고 하더라도 Servlet을 거쳐서 화면을 띄워주자!!
        		// => url에 서블릿 매필값만 노출되겠지?
        		// localhost:8001/jsp/매핑값
        		location.href = "<%= contextPath %>/enrollForm.me"
        	}
        
        </script>
	<% } else { %>
		<!-- 로그인 후 보여지는 화면 -->
            <div id="user-info">
                <b><%= loginUser.getUserName() %> 님</b> 환영합니다. <br><br>
                <div>
                    <a href="<%= contextPath %>/mypage.me">마이페이지</a>
                    <a href="<%= contextPath %>/logout.me">로그아웃</a>
                </div>
            </div>
	<% } %>
    </div>

    <br clear="both">
    <br>

    <div class="nav-area" align="center">
        <div class="menu"><a href="">HOME</a></div>
        <div class="menu"><a href="">공지사항</a></div>
        <div class="menu"><a href="">일반게시판</a></div>
        <div class="menu"><a href="">사진게시판</a></div>
    </div>
</body>
</html>