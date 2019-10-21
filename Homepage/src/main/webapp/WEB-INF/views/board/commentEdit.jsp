<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link href="/resources/css/board/commentEdit.css" rel="stylesheet">
    <script src="/resources/js/board/commentEdit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <title>명지대학교 융합소프트웨어학부 : 댓글 수정</title>
</head>

<div id="commentEdit" class="container">
    <div class="container">
        <form:form id="commentEditForm" method="post" modelAttribute="comment" action="/board/commentEdit" autocomplete="off">
            <div class="form-group">
                <label for="commentContent"><strong>댓글 작성</strong></label>
                <form:textarea id="commentContent" class="form-control" name="commentContent" path="commentContent" cols="10" placeholder="댓글은 1~100자 이내로 작성할 수 있습니다."
                               onKeyup="checkContentLength()" onblur="hideErrorContent()"/>
                <form:errors path="commentContent" id="errorContent"/>
                <span id="contentCounter">(0 / 100자)</span>
            </div>

            <br/>

            <input type="hidden" name="commentId" value="${comment.commentId}"/>
            <input type="hidden" name="commentPostId" value="${comment.commentPostId}"/>
            <button id="commentEditButton" type="submit" class="btn btn-warning">
                <strong>등록</strong></button>
        </form:form>
    </div>

    <c:if test="${serverMessage != null}">
        <script>
            var message = "${serverMessage}";
            alert(message)
        </script>
    </c:if>
</div>