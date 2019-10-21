<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>명지대학교 융합소프트웨어학부 : 로그인</title>
    <link href="/resources/css/user/login.css" rel="stylesheet">
    <script src="/resources/js/user/login.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="login" class="container">
    <div id="loginHeader" class="container">
        <h4>로그인</h4>
        <p>ID와 비밀번호를 입력하여 로그인하세요.</p>
    </div>

    <br/>

    <div id="loginForm" class="container">
        <form method="post" action="/user/login">
            <div class="form-group">
                <input id="userId" class="form-control" name="userId" type="text" value="admin1" placeholder="ID">
            </div>
            <div class="form-group">
                <input id="userPassword" class="form-control" name="userPassword" type="password" value="test123!" placeholder="Password">
            </div>

            <br/>

            <div id="loginFormFooter" class="form-group">
                <button id="loginButton" class="btn btn-warning btn-block" type="submit">
                    <strong>로그인</strong>
                </button>
            </div>
        </form>
    </div>

    <div id="loginFooter" class="d-flex bd-highlight mb-3">
        <div class="mr-auto p-1 bd-highlight"><a href="/user/join">회원 가입</a></div>
        <div class="p-1 bd-highlight"><a href="/">이메일/비밀번호 찾기</a></div>
    </div>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>