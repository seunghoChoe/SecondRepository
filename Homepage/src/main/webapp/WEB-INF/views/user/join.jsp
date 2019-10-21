<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>명지대학교 융합소프트웨어학부 : 회원가입</title>
    <link href="/resources/css/user/join.css" rel="stylesheet">
    <script src="/resources/js/user/join.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="join" class="container">
    <div id="joinHeader" class="container">
        <h4>회원 가입</h4>
        <p>가입 정보를 입력하여 계정을 등록하세요.</p>
    </div>

    <br/>

    <div id="joinForm" class="container">
        <form:form method="post" modelAttribute="user" class="join" action="/user/join">
            <div class="form-group">
                <label for="userId">ID</label>
                <form:input path="userId" type="text" class="form-control" id="userId" name="userId" placeholder="ID를 입력해주세요." onblur="checkUserId()"/>
                <form:errors path="userId" id="userIdError"/>
                <span id="userIdMessage"></span>
            </div>

            <div class="form-group">
                <label for="userPassword">비밀번호</label>
                <form:input path="userPassword" type="password" class="form-control" id="userPassword" name="userPassword" placeholder="비밀번호를 입력해주세요." onblur="checkUserPassword()"/>
                <form:errors path="userPassword" id="userPasswordError"/>
            </div>

            <div class="form-group">
                <label for="confirmPassword">비밀번호 확인</label>
                <input type="password" class="form-control" id="confirmPassword" placeholder="비밀번호를 확인해주세요." onblur="checkUserPassword()"/>
                <span id="userPasswordMessage"></span>
            </div>

            <br/>

            <div class="form-group">
                <label for="userName">이름</label>
                <form:input path="userName" type="text" class="form-control" id="userName" name="userName" placeholder="이름을 입력해주세요." onblur="checkUserName()"/>
                <form:errors path="userName" id="userNameError"/>
                <span id="userNameMessage"></span>
            </div>

            <div class="form-group">
                <label for="userNumber">학번</label>
                <form:input path="userNumber" type="text" class="form-control" id="userNumber" name="userNumber" placeholder="학번을 입력해주세요." onblur="checkUserNumber()"/>
                <form:errors path="userNumber" id="userNumberError"/>
                <span id="userNumberMessage"></span>
            </div>

            <div class="form-group">
                <label for="userEmail">이메일</label>
                <form:input path="userEmail" type="email" class="form-control" id="userEmail" name="userEmail" placeholder="이메일을 입력해주세요." onblur="checkUserEmail()"/>
                <form:errors path="userEmail" id="userEmailError"/>
                <span id="userEmailMessage"></span>
            </div>

            <br/>

            <button id="joinButton" type="submit" class="btn btn-warning">
                <strong>회원 가입</strong></button>
            <button id="joinCancelButton" type="button" class="btn btn-default" onclick="location.href='/'">
                <strong>취소</strong></button>
        </form:form>
    </div>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>