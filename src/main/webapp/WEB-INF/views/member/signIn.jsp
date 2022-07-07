<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignIn</title>
</head>
<body>
	<h3 align="center">회원가입</h3>
	<form method="post" name="form" action="${path}/signInAction.do">
		<table align="center">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="mem_id" size="30" placeholder="아이디를 입력하세요.">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="mem_pwd" size="30" placeholder="비밀번호를 입력하세요.">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" name="signIn" value="회원가입">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>