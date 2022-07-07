<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignInAction</title>
</head>
<body>
	<div>
		<!-- service에서 받아온 값이 0이면 회원가입 실패 -->
		<c:if test="${insertCnt==0}">
			<script type="text/javascript">
				alert("회원가입 실패");
			</script>
		<!-- service에서 받아온 값이 1이면 회원가입 성공 ==> 값 들고 signInSuccess.do로 이동하기 -->
		</c:if>
		<c:if test="${insertCnt==1}">
			<c:redirect url="signInSuccess.do?insertCnt=${insertCnt}" />
		</c:if>
	</div>

</body>
</html>