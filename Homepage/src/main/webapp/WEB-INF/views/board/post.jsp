<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <link href="/resources/css/board/post.css" rel="stylesheet">
    <script src="/resources/js/board/post.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <title>명지대학교 융합소프트웨어학부 : 게시글 조회</title>
</head>

<!-- 게시글 영역 -->
<div id="post" class="container">
    <div id="postHeader" class="card text-center">
        <div class="card-header mt-2">
            <h3><strong>게시글 조회</strong></h3>
        </div>
        <div class="card-body">
            작성자 : ${post.postUserId} <br>
            작성일 : <fmt:formatDate value="${post.postCreatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/> <br>
            수정일 : <fmt:formatDate value="${post.postUpdatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/> <br>
        </div>
        <hr/>
        <div class="card-footer text-muted mb-2">
            좋아요 : ${post.postLike} <br>
            조회수 : ${post.postView} <br>
        </div>
    </div>

    <div id="postBody" class="container">
        <div id="postTitle" class="container">
            <h4>제목</h4>
            <p>${post.postTitle}</p>
        </div>
        <div id="postContent" class="container">
            <h4>내용</h4>
            <p>${post.postContent}</p>
        </div>
    </div>

    <c:if test="${loggedUser != null}">
        <c:if test="${post.postUserId == loggedUser.userId}">
            <button id="postEditButton" type="button" class="btn btn-warning" onclick="postEdit(${post.postId})">
                <strong>수정</strong></button>
            <button id="postDeleteButton" type="button" class="btn btn-danger" onclick="postDelete(${post.postId})">
                <strong>삭제</strong></button>
        </c:if>
        <c:if test="${post.postUserId != loggedUser.userId}">
            <button id="postLikeButton" type="button" class="btn btn-warning" onclick="postLike(${post.postId})">
                <strong>좋아요</strong></button>
        </c:if>
    </c:if>
    <button id="postListButton" type="button" class="btn btn-warning" onclick="location.href='/board/postList'">
        <strong>목록</strong></button>
</div>

<div id="postFooter" class="container">
    <%-- 댓글 영역 --%>
    <c:forEach var="comment" items="${commentList}">
        <div class="commentBody">
            <h5>${comment.commentUserId}
                <small class="text-muted">(작성일: <fmt:formatDate value="${comment.commentCreatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></small>
                <small class="text-muted">수정일: <fmt:formatDate value="${comment.commentUpdatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>)</small>
            </h5>

            <p>${comment.commentContent}</p>
        </div>
        <c:if test="${comment.commentUserId == loggedUser.userId}">
            <div class="commentFooter">
                <button id="commentEditButton" type="button" class="btn btn-warning btn-sm" onclick="commentEdit(${comment.commentPostId}, ${comment.commentId})">
                    <strong>수정</strong></button>
                <button id="commentDeleteButton" type="button" class="btn btn-danger btn-sm" onclick="commentDelete(${comment.commentPostId}, ${comment.commentId})">
                    <strong>삭제</strong></button>
            </div>
        </c:if>
        <c:if test="${comment.commentUserId != loggedUser.userId}">
            <div class="commentFooter">
                <button id="commentLikeButton" type="button" class="btn btn-warning btn-sm" onclick="commentLike(${comment.commentId})">
                    <strong>좋아요</strong></button>
            </div>
        </c:if>
    </c:forEach>
</div>

<%-- 댓글 등록 폼 --%>
<c:if test="${loggedUser != null}">
    <jsp:include page="newComment.jsp"/>
</c:if>

<%--<c:if test="${serverMessage != null}">--%>
<%--    <script>--%>
<%--        var message = "${serverMessage}";--%>
<%--        alert(message)--%>
<%--    </script>--%>
<%--</c:if>--%>