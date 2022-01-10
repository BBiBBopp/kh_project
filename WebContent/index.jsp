<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>
	<!-- 
		* CRUD : 대부분의 컴퓨터 소프트웨어가 가지는 기본적인 데이터 처리 기능을 묶어서 일컫는 말
				 사용자 인터페이스가 갖추어야만 하는 기능을 가리키는 용어로써도 사용된다.
				 - C : Create(생성) => INSERT
				 - R : Read (읽기/인출) => SELECT
				 - U : Update(갱신) => UPDATE
				 - D : Delete(파괴/삭제) => DELETE
		
		* 회원서비스
		로그인(R), 회원가입(C)-(아이디중복체크(R)), 마이페이지(R), 내정보변경(U), 회원탈퇴(D/U)
		
		* 공지사항서비스
		공지사항리스트조회(R), 공지사항상세조회(R), 공지사항등록(C), 공지사항수정(U), 공지사항삭제(D/U)
		
		* 일반게시판서비스
		게시판리스트조회(R)-페이징처리, 게시판상세조회(R), 게시판작성(C)-첨부파일 업로드(1개 업로드),
		게시판 수정(U), 게시판 삭제(D/U) // (댓글리스트 조회(R), 댓글작성(C))
		
		* 사진게시판서비스
		사진게시판리스트조회(R)-썸네일, 사진게시판상세조회(R), 사진게시판작성(C)-다중파일업로드
	 -->
	 
	<!-- 상단에는 menubar.jsp가 보이게 해줄 것 -->
	<%@ include file="views/common/menubar.jsp" %>

</body>
</html>