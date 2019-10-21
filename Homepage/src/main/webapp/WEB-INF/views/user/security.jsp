<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>명지대학교 융합소프트웨어학부 : 회원 정보 수정</title>
    <link href="/resources/css/user/security.css" rel="stylesheet">
    <script src="/resources/js/user/security.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="security" class="container">
    <div id="securityHeader" class="container">
        <h4>비밀번호 수정</h4>
        <p>계정 비밀번호를 수정할 수 있습니다.</p>
    </div>

    <br/>

    <fieldset id="securityForm">
        <form:form method="post" modelAttribute="user" action="/user/security" autocomplete="off">
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

            <input type="hidden" name="userId" value="${loggedUser.userId}"/>
            <button id="securityButton" type="submit" class="btn btn-warning">
                <strong>비밀번호 수정</strong></button>
            <button id="securityCancelButton" type="button" class="btn btn-default" onclick="location.href='/user/info'">
                <strong>취소</strong></button>
        </form:form>
    </fieldset>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>