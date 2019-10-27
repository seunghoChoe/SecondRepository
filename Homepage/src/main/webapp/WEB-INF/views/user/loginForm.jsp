<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="/resources/css/userForm.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/loginForm.js?v=<%=System.currentTimeMillis() %>"></script>
    <title>명지대학교 융합소프트웨어학부 : 로그인</title>
</head>

<body>
    <div class="jumbotron" id="login_jumbotron">
        <h1>로그인</h1>
        <p><i class="fas fa-user-circle fa-6x"></i></p>

        <c:url var="loginPath" value="/user/login"/>
        <form:form class="loginForm" method="post" action="${loginPath}" modelAttribute="loginForm" autocomplete="off">
            <div class="form-group">
                <h3 class="form-group-heading">환영합니다.</h3>
                <h3><small>아이디와 비밀번호를 입력하여 로그인하세요.</small></h3>
            </div>

            <div class="form-group">
                <label for="userId">ID</label>
                <form:input path="userId" type="text" class="form-control" id="userId" name="userId" placeholder="ID를 입력해주세요." oninput="checkId()"/>
                <form:errors path="userId" id="errorId"/>
            </div>

            <div class="form-group">
                <label for="userPw">비밀번호</label>
                <form:input path="userPw" type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호를 입력해주세요." oninput="checkPw()"/>
                <form:errors path="userPw" id="errorPw"/>
            </div>

            <div class="btn-group">
                <p>
                    <button type="submit" class="btn btn-default" id="loginBtn">로그인</button>
                    <button type="button" class="btn btn-default" id="indexBtn" onclick="window.location='/'">홈</button>
                </p>
            </div>
        </form:form>

        <c:if test="${msg != null}">
            <br>
            <div class="alert alert-danger">
                <strong>로그인 실패!</strong> ${msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
    </div>
</body>