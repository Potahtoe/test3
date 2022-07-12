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
	<h3 align="center">관리자 페이지</h3>
	<!-- UserDeniedHandler에서 errMsg 전달 -->
         <p style="font-size: 18px">${errMsg}</p> <br>
         
         <c:if test="${sessionScope.sessionID !=null}">
            <button value="이동하기" class="inputButton" style="width:200px; font-size:24px; text-align: center;"
               onclick="window.location='logout.do'">이동하기</button>
         </c:if>
         <c:if test="${sessionScope.sessionID == null}">
            <button value="이동하기" class="inputButton" style="width:200px; font-size:24px; text-align: center;"
               onclick="window.location='login.do'">이동하기</button>
         </c:if>
</body>
</html>