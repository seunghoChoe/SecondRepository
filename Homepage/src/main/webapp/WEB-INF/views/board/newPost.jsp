<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link href="/resources/css/board/newPost.css" rel="stylesheet">
    <script src="/resources/ckeditor4/ckeditor.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <script src="/resources/js/board/newPost.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <title>명지대학교 융합소프트웨어학부 : 게시글 등록</title>
</head>

<div id="newPost" class="container">
    <div id="newPostHeader" class="container">
        <h4>게시글 작성</h4>
        <p>게시글 작성</p>
    </div>

    <br/>

    <div id="newPostForm" class="container">
        <form:form method="post" modelAttribute="post" action="/board/newPost" autocomplete="off">
            <div class="form-group">
                <label for="postTitle">게시물 제목</label>
                <form:input id="postTitle" class="form-control" name="postTitle" path="postTitle" type="text" placeholder="게시글 제목은 1~100자 이내로 작성할 수 있습니다."
                            onKeyup="checkTitleLength()" onblur="hideErrorTitle()"/>
                <form:errors path="postTitle" id="errorPostTitle"/>
                <span id="titleCounter">(0 / 100자)</span>
            </div>

            <br/>

            <div class="form-group">
                <label for="postContent">게시물 내용</label>
                <form:textarea id="postContent" class="form-control" name="postContent" path="postContent" cols="10" placeholder="게시글 내용은 1~2500자 이내로 작성할 수 있습니다."
                               onblur="hideErrorContent()"/>
                <form:errors path="postContent" id="errorPostContent"/>
            </div>

            <br/>

            <button id="newPostButton" type="submit" class="btn btn-warning">
                <strong>등록</strong></button>
            <button id="newPostCancelButton" type="button" class="btn btn-default" onclick="location.href='/board/postList'">
                <strong>취소</strong></button>
        </form:form>
    </div>

    <c:if test="${serverMessage != null}">
        <script>
            var message = "${serverMessage}";
            alert(message)
        </script>
    </c:if>
</div>