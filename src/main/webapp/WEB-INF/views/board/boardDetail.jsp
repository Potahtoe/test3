<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
<script>
	$(function(){
		$("#delete").click(function(){
			document.form.action="${path}/boardDeleteAction.do";
			document.form.submit();
		});
		$("#update").click(function(){
			document.form.action="${path}/boardUpdate.do?board_no=${dto.board_no}&pageNum=${crtPage}&searchContent=${searchContent}";
			document.form.submit();
		});
		
		$("#list").click(function(){
			var searchContent =$('input[name=searchContent]').val();
			
			if(searchContent==""){
					location.href="${path}/boardList.do?board_no=${dto.board_no}&pageNum=${crtPage}&searchContent=${searchContent}";
			}else{
					location.href="${path}/boardSearch.do?board_no=${dto.board_no}&pageNum=${crtPage}&searchContent=${searchContent}";
			}
		});
	});

</script>
</head>
<body>
	<h3 align="center">게시판 상세</h3>
	<form method="post" name="form">
		<table align="center">
			<tr>
				<th>제목</th>
				<td><textarea rows="1" cols="100" name="board_title" readonly>${dto.board_title}</textarea></td>
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
				<td><textarea rows="10" cols="100" name="board_contents" readonly>${dto.board_contents}</textarea></td>
			</tr>
			<tr colspan="2" align="center">
				<td>
				<input type="hidden" name="searchContent" value="${searchContent}"> 
				<input type="hidden" name="crtPage" value="${crtPage}"> 
				<input type="hidden" name="board_no" value="${dto.board_no}"> 
				<input type="button" id="delete" value="삭제">
				<input type="button" id="update" value="수정">
				<input type="button" id="list" value="목록">
				<td>
			</tr>
		</table>
	</form>
</body>
</html>