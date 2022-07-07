<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
<script>
	$(function(){
		$("#insert").click(function(){
			document.form.action="${path}/boardInsertAction.do";
			document.form.submit();
		});
	});
</script>
</head>
<body>
	<h3 align="center">게시판 등록</h3>
	<form method="post" name="form">
		<table align="center">
			<tr>
				<th>제목</th>
				<td><textarea rows="1" cols="100" name="board_title" maxlength="100" placeholder="제목을 입력하세요." autofocus required></textarea></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><textarea rows="1" cols="100" name="board_writer" readonly>${sessionID}</textarea></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea style="resize:vertical;" rows="10" cols="100" name="board_contents" maxlength="2000" placeholder="내용을 입력하세요." required></textarea></td>
			</tr>
			<tr align="right">
				<td>
					<input type="button" id="insert" value="등록">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>