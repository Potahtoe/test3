<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판_목록</title>
<script>
	$(function(){
		$("#insert").click(function(){
			location.href="${path}/boardInsert.do";
		});
		$("#search").click(function(){
			document.form.action="${path}/boardSearch.do";
			document.form.submit();
		});
	});
</script>
</head>
<body>
	<h3 align="center">게시판 목록</h3>
	<form method="post" name="form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<table align="center">
			<tr>
				<td colspan="5" align="right">
					<input type="text" name="searchContent" size="30" placeholder="검색어를 입력하세요.">
					<input type="button" id="search" value="검색">
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
			<c:set var="num" value="${total-((paging.currentPage-1)*10)}"/>
			<c:forEach var="dto" items="${list}">
				<tr>
					<th>${num}</th>
					<c:choose>
						<c:when test="searchContent==null">
							<th><a href="${path}/boardDetail.do?board_no=${dto.board_no}&crtPage=${paging.currentPage}">${dto.board_title}</a></th>
						</c:when>
						<c:otherwise>
							<th><a href="${path}/boardDetail.do?board_no=${dto.board_no}&crtPage=${paging.currentPage}&searchContent=${searchContent}">${dto.board_title}</a></th>
						</c:otherwise>
					</c:choose>
					<th>${dto.board_writer}</th>
					<th>${dto.read_cnt}</th>
					<th>${dto.in_date}</th>
				</tr>
				<c:set var="num" value="${num-1}" />
			</c:forEach>
          	<!-- 페이징 처리 -->
			<tr>
	           	<td colspan="5" align="center">
		          	<!-- 이전버튼 활성화 여부 -->
		          	<c:if test="${paging.startPage>10}">
			          	<c:choose>
							<c:when test="searchContent==null">
		          				<a href="${path}/boardList.do?pageNum=${paging.prev}">[이전]</a>
		          			</c:when>
		          			<c:otherwise>
		          				<a href="${path}/boardSearch.do?pageNum=${paging.prev}">[이전]</a>
		          			</c:otherwise>
		          		</c:choose>
		          	</c:if>
		          	
		          	<!-- 페이지 번호 처리 -->
		          	<c:forEach var="pgnum" begin="${paging.startPage}" end="${paging.endPage}">
		          		<c:choose>
							<c:when test="searchContent==null">
		          				<a href="${path}/boardList.do?pageNum=${pgnum}">${pgnum}</a>
		          			</c:when>
		          			<c:otherwise>
		          				<a href="${path}/boardSearch.do?pageNum=${pgnum}&searchContent=${searchContent}">${pgnum}</a>
		          			</c:otherwise>
		          		</c:choose>
		          	</c:forEach>
		          	
		          	<!-- 다음 버튼 활성화 여부 -->
		          	<c:if test="${paging.endPage<paging.pageCount}">
		          		<c:choose>
							<c:when test="searchContent==null">
		          				<a href="${path}/boardList.do?pageNum=${paging.next}">[다음]</a>
		          			</c:when>
		          			<c:otherwise>
		          				<a href="${path}/boardSearch.do?pageNum=${paging.next}">[다음]</a>
		          			</c:otherwise>
		          		</c:choose>
		          	</c:if>
	          	</td>
            </tr>
			<tr>
				<td colspan="5" align="right">
					<input type="hidden" name="searchContent" value="${searchContent}">
					<input type="hidden" name="crtPage" value="${paging.currentPage}">
					<input type="button" id="insert" value="글쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>