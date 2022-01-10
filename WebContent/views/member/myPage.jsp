<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
<style>
    .outer{
        background-color: lightgray;
        color: white;
        width: 500px;
        margin: auto;
        margin-top: 50px;
        margin-bottom: 50px;
    }
    table{
        margin: auto;
    }
    input{
        margin: 5px;
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<%
		String loginId = loginUser.getUserId();
		String loginName = loginUser.getUserName();
		// 필수 입력 조건이 아닌 정보는 null이 들어올 수 있음
		String phone = (loginUser.getPhone() != null) ? loginUser.getPhone() : "";
		String email = (loginUser.getEmail() != null) ? loginUser.getEmail() : "";
		String address = (loginUser.getAddress() != null) ? loginUser.getAddress() : "";
		// "운동, 등산, ..."
		String interests = (loginUser.getInterest() != null) ? loginUser.getInterest() : ""; 
		
		
		
	%>
	
    <div class="outer">
        <br>

        <h2 align="center">마이페이지</h2>
        <form action="<%= contextPath %>/update.me" method="post">
            <!-- 아이디, 비밀번호, 이름, 전화번호, 이메일주소, 주소, 취미 -->
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" value="<%=loginId %>" name="userId" maxlength="12" readonly></td>
                    <td><button class="btn btn-secondary btn-sm">중복확인</button></td>
                    
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" value="<%=loginName %>" name="userName" maxlength="6" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" value="<%=phone %>" name="phone" placeholder="-포함해서 입력"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" value="<%=email %>" name="email"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" value="<%=address %>" name="address"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;관심분야</td>
                    <td colspan="2">
                        <input type="checkbox" name="interest" value="운동" id="sports"> <label for="sports">운동</label>
                        <input type="checkbox" name="interest" value="등산" id="hiking"> <label for="hiking">등산</label>
                        <input type="checkbox" name="interest" value="낚시" id="fishing"> <label for="fishing">낚시</label>
                        
                        <br>

                        <input type="checkbox" name="interest" value="요리" id="cooking"> <label for="cooking">요리</label>
                        <input type="checkbox" name="interest" value="게임" id="game"> <label for="game">게임</label>
                        <input type="checkbox" name="interest" value="영화" id="movie"> <label for="movie">영화</label>
                    </td>
                </tr>
            </table>

			<script>
				$(function(){
					var interests = "<%= interests %>";
					
					// 모든 체크박스를 선택해보자
					$("input[type=checkbox]").each(function(){
						// 순차적으로 접근한 checkbox의 속성값이 interest에 포함되어 있을 경우에만 체크
						// => checked 속성 부여 => attr(속성명, 속성값);
						
						// 자바스크립트의 indexOf => 찾고자하는 문자가 없는 경우 -1을 리턴 == jQuery의 search메소드
						// jQuery에서 value속성값을 리턴해주는 메소드 : val()
						// 제이쿼리에서 현재 접근한 요소 지칭: $(this)
						if(interests.search($(this).val()) != -1){ // 포함되어 있을 경우 checked 속성 부여
							$(this).attr("checked", true);
						}
					
					})
				})	
			</script>
            
            <br><br>
            <div align="center">
                <button type="submit" class="btn btn-secondary btn-sm">정보 변경</button>
                <button type="button" data-toggle="modal" data-target="#updatePwdForm" class="btn btn-info btn-sm">비밀번호 변경</button>
                <button type="button" data-toggle="modal" data-target="#deleteMemberForm" class="btn btn-danger btn-sm">회원 탈퇴</button>
            </div>
            <br><br><br>
        </form>

    </div>

    <!-- The Modal : 비밀번호 변경 -->
    <div class="modal" id="updatePwdForm">
        <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
            <h4 class="modal-title">비밀번호 변경</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form action="<%= contextPath %>/updatePwd.me" method="post">
                    <!-- 현재 비밀번호, 변경할 비밀번호, 비밀번호 확인(재입력) -->
                    <!-- 확실하게 비밀번호의 주인을 판별할 수 있는 id도 같이 넘겨주자 -->
                    <input type="hidden" name="userId" value="<%= loginId %>">
                    
                    <table>
                        <tr>
                            <td>현재 비밀번호 </td>
                            <td><input type="password" name="userPwd" required></td>
                        </tr>
                        <tr>
                            <td>변경할 비밀번호 </td>
                            <td><input type="password" name="updatePwd" required></td>
                        </tr>
                        <tr>
                            <td>비밀번호 재입력 </td>
                            <td><input type="password" name="checkPwd" required></td>
                        </tr>
                    </table>
                    <br>
                    <button type="submit" class="btn btn-info btn-sm">비밀번호 변경</button>
                </form>
            </div>

        </div>
        </div>
    </div>

    <script>
        function validatePwd(){
            if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()){
                return false;
            }

            return true;
        }
    </script>


    <!-- The Modal : 회원 탈퇴 -->
    <div class="modal" id="deleteMemberForm">
        <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
            <h4 class="modal-title">회원 탈퇴</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
            	<b>
            		탈퇴 후 복구가 불가능합니다.<br>
            		정말 탈퇴하시겠습니까? <br><br>
            	</b>
                <form action="<%= contextPath %>/deleteMember.me" method="post">
                    <!-- 현재 비밀번호 입력 -->
                    <!-- 확실하게 비밀번호의 주인을 판별할 수 있는 id도 같이 넘겨주자 -->
                    <input type="hidden" name="userId" value="<%= loginId %>">
                            <span>비밀번호를  입력해주세요. </span>
                            <input type="password" name="userPwd" required>
                    <br><br><br>
                    <div align="center">
                        <button type="submit" class="btn btn-info btn-sm">탈퇴하기</button>
                    </div>
                </form>
            </div>

        </div>
        </div>
    </div>
	
</body>
</html>