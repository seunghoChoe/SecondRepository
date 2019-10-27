<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<link href="/resources/css/boardDetail.css" rel="stylesheet">
	<script type="text/javascript" src="/resources/js/boardDetail.js?v=<%=System.currentTimeMillis() %>"></script>
	<title>명지대학교 융합소프트웨어학부 : 게시글 상세보기</title>
</head>

<body>

	<!-- 게시글 영역 -->
	<div align="center" class="body">
		<table class="table table-bordered">

			<thead align="center">
				<tr>
					<td colspan="2" class="boardHeader">
						게시글 상세보기
					</td>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td align="right" colspan="2">
						작성일 : <fmt:formatDate value="${board.boardCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/> <br>
						수정일 : <fmt:formatDate value="${board.boardUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/> <br>
						좋아요 : ${board.boardLike} <br>
					</td>
				</tr>

				<tr>
					<th class="boardTitle">제목 </th>
					<td>
						${board.boardTitle}
					</td>
				</tr>

				<tr>
					<th>작성자</th>
					<td>
						${board.boardAuthor}
					</td>
				</tr>

				<tr>
					<th class="boardContent">내용 </th>
					<td>
						${board.boardContent}
					</td>
				</tr>

			</tbody>
		</table>

		<c:if test="${loggedUser != null}">
			<input type="button" value="글쓰기" onclick="window.location ='/board/createBoard'"/>

			<c:if test="${board.boardAuthor == loggedUser.userId}">
				<input type="button" value="게시글 수정" onclick="window.location ='/board/updateBoard?boardIdx=${board.boardIdx}'"/>
				<input type="button" value="게시글 삭제" onclick="onPopupDelete()"/>
			</c:if>

			<c:if test="${board.boardAuthor != loggedUser.userId}">
				<input type="button" value="좋아요" onclick="window.location ='/board/checkBoardLike?boardIdx=${board.boardIdx}'"/>
			</c:if>
		</c:if>
		<input type="button" value="게시글 목록" onclick="window.location ='/board/boardList'"/>

		<script type="text/javascript">
			function onPopupDelete() {
				var del = confirm("게시글을 삭제하시겠습니까?");
				if (del == true){
					location.href = "/board/deleteBoard?boardIdx=${board.boardIdx}"
					alert("게시글이 삭제되었습니다.");
				}else{
					alert("취소되었습니다.")
				}
			}
			$(function() {
				var msg = "<c:out value="${msg}" />";
				if (msg != "") {
					alert(msg)
				}
			});
		</script>
	</div>

	<c:if test="${loggedUser != null}">
		<div style="padding:10px">
			<form name="createComment" method="post" action="/board/createComment">
				<table>
					<tr>
						<td>
							<textarea cols="100" rows="5" placeholder="댓글을 입력하세요." name="commentContent" id="commentContent" style="display:inline-block;  width:500px; height:70px; resize:none;"></textarea>
						</td>

						<td>
							<input type="hidden" name="boardIdx" value="${board.boardIdx}"/>
							<input type="hidden" name="commentAuthor" value="${loggedUser.userId}"/>
							<input type="button" value="등록" onclick="onCreateComment()"
								   style="display:inline-block; width:100px; height:70px"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</c:if>

	<div>
		<!-- 댓글 영역 -->
		<table class="table table-bordered">
			<c:forEach items="${commentList}" var="comment">
				<tr>
					<th style="width:60px">작성자: </th>
					<td style="width:60px">${comment.commentAuthor}</td>
					<th style="width:60px">좋아요: </th>
					<td style="width:180px">${comment.commentLike}</td>
					<th style="width:60px">작성일: </th>
					<td><fmt:formatDate value="${comment.commentCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<th style="width:60px">수정일: </th>
					<td><fmt:formatDate value="${comment.commentUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td colspan="6">${comment.commentContent}</td>
					<td colspan="2" style="text-align:center">
						<c:if test="${comment.commentAuthor == loggedUser.userId}">
							<input type="button" value="수정"
								   onclick="window.location ='/board/updateComment?commentIdx=${comment.commentIdx}'"
								   style="display:inline-block; width:50px; height:20px">
							<input type="button" value="삭제"
								   onclick="window.location ='/board/deleteComment?commentIdx=${comment.commentIdx}'"
								   style="display:inline-block; width:50px; height:20px"/>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>