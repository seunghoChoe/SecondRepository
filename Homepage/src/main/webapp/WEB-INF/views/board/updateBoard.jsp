<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
	<link href="/resources/css/updateBoard.css" rel="stylesheet">
	<script type="text/javascript" src="/resources/ckeditor4/ckeditor.js?v=<%=System.currentTimeMillis() %>"></script>
	<script type="text/javascript" src="/resources/js/updateBoard.js?v=<%=System.currentTimeMillis() %>"></script>
	<title>명지대학교 융합소프트웨어학부 : 게시글 수정</title>
</head>

<body>
	<div align="center" class="body">

		<form:form method="post" modelAttribute="updateBoard" action="/board/updateBoard">
			<table class="table table-bordered">

				<thead align="center">
					<tr>
						<td colspan="2" class="boardHeader">
							게시글 수정
						</td>
					</tr>
				</thead>

				<tbody>
					<tr>
						<th>제목</th>
						<td>
							<form:input path="boardTitle" type="text" placeholder="제목은 한글/영문 100자로 제한됩니다." value ="${board.boardTitle}"
										name="boardTitle" id="boardTitle" class="form-control" onKeyup="checkTitleLength()" onblur="checkTitle()"/>
							<form:errors path="boardTitle" id="errorTitle"/>
							<span id="titleCounter">(0 / 100자)</span>
						</td>
					</tr>

					<tr>
						<th>작성자</th>
						<td>
						<form:input path="boardAuthor" type="hidden" value="${loggedUser.userId}" name="boardAuthor" class="form-control"/>
							${loggedUser.userId}
						</td>
					</tr>

					<tr>
						<th>내용 </th>
						<td>
							<form:textarea path="boardContent" cols="10" placeholder="내용은 한글/영문 1000자로 제한됩니다." value ="${board.boardContent}"
										   name="boardContent" id="boardContent" class="form-control" onKeyup="checkContentLength()" onblur="checkContent()"/>
							<form:errors path="boardContent" id="errorContent"/>
							<span id="contentCounter">(0 / 1000자)</span>
						</td>
					</tr>

					<tr>
						<td colspan="2">
							<form:input path="boardIdx" name="boardIdx" type="hidden" value="${board.boardIdx}"/>
							<input type="button" class="pull-right" value="취소" onclick="location.href='/board/boardList'">
							<input type="submit" class="pull-right" value="수정">
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>

	</div>
	<script src="/resources/ckeditor4/ckeditor.js"></script>

</body>