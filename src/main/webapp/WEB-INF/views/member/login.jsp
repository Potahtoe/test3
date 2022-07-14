<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<script>
	$(function(){
		$("#signIn").click(function(){
			location.href="${path}/signIn.do";
		});
		$("#login").click(function(){
			document.form.action="${path}/login_check.do";
			document.form.submit();
		})
	});
</script>
</head>
<body>
	<c:if test="${errorMsg!=null}">
		<script type="text/javascript">
			alert("${errorMsg}");
		</script>
	</c:if>
	<h3 align="center">로그인</h3>
	<form method="post" name="form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<table align="center">
			<tr>
				<th>아이디</th>
				<td><input type="text" class="input" name="mem_id" size="30" placeholder="아이디를 입력하세요." autofocus required></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" class="input" name="mem_pwd" size="30" placeholder="비밀번호를 입력하세요." required></td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="right">
						<input type="button" id="login" value="로그인">
						<input type="button" id="signIn" value="회원가입">
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>