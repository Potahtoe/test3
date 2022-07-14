<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
<script>
	$(function(){
		$("#update").click(function(){
			document.form.action="${path}/boardUpdateAction.do"
			document.form.submit();
		});
	});
</script>
</head>
<body>
	<h3 align="center">게시판 수정</h3>
	<form method="post" name="form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<table align="center">
			<tr>
				<th>제목</th>
				<td><textarea rows="1" cols="100" name="board_title" maxlength="100" autofocus required>${dto.board_title}</textarea></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.board_writer}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.read_cnt}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.in_date}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="100" name="board_contents" maxlength="2000" required>${dto.board_contents}</textarea></td>
			</tr>
			<tr>
				<td align="right">
					<input type="hidden" name="searchContent" value="${searchContent}">
					<input type="hidden" name="pageNum" value="${crtPage}">
					<input type="hidden" name="board_no" value="${dto.board_no}">
					<input type="button" id="update" value="수정">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>