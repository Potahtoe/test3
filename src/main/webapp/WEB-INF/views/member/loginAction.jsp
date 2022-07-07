<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
<!-- 로그인 성공 시 게시판 목록 조회 페이지로 이동 -->
<div>
	<c:if test="${selectCnt==1}">
		<script type="text/javascript">
			location.href="${path}/boardList.do";
		</script>		
	</c:if>
<!-- 로그인 실패 시 로그인 페이지로 이동 -->
	<c:if test="${selectCnt!=1}">
		<script type="text/javascript">
			location.href="${path}/login.do";
		</script>		
	</c:if>
</div>
</body>
</html>