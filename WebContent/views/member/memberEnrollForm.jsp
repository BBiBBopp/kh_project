<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    .outer{
        background-color: lightgray;
        width: 500px;
        margin: auto;
        margin-top: 100px;
    }
    table{margin: auto;}
    input{margin: 5px;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

    <div class="outer">
        <br>
        <h2 align="center">회원가입</h2>

        <form action="<%= contextPath %>/insert.me" method="post">
            <!-- 아이디, 비밀번호, 이름, 전화번호, 이메일주소, 주소, 취미 -->
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td> <input type="text" name="userId" maxlength="12" required></td>
                    <td><button type="button">중복확인</button></td>
                    <!-- 중복확인은 Ajax 배우고나서 할 것!! -->
                </tr>
                <tr>
                    <td>* 비밀번호</td>
                    <td><input type="password" name="userPwd" maxlength="15" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>* 비밀번호 확인</td>
                    <td><input type="password" maxlength="15" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="6" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" name="phone" placeholder="-포함해서 입력"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" name="email"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" name="address"></td>
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

            <br><br>
            <div align="center">
                <button type="submit">회원가입</button>
                <button type="reset">초기화</button>
            </div>
        </form>
        
        <br><br><br>
    </div>
</body>
</html>