<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>명지대학교 융합소프트웨어학부 : 계정 정보 수정</title>
    <link href="/resources/css/user/edit.css" rel="stylesheet">
    <script src="/resources/js/user/edit.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
</head>

<div id="edit" class="container">
    <div id="editHeader" class="container">
        <h4>계정 정보 수정</h4>
        <p>가입 시 입력한 정보를 수정할 수 있습니다.</p>
    </div>

    <br/>

<%-- 모델을 xxxEdit 에서 user 로 변경함 --%>
    <fieldset id="editForm">
        <form:form method="post" modelAttribute="user" action="/user/nameEdit" autocomplete="off">
            <div class="form-group">
                <label for="userName">이름</label>
                <form:input path="userName" type="text" class="form-control" id="userName" name="userName" value="${userName}" placeholder="이름을 입력해주세요." onblur="checkUserName()"/>
                <form:errors path="userName" id="userNameError"/>
                <span id="userNameMessage"></span>
            </div>

            <input type="hidden" name="userId" value="${loggedUser.userId}"/>
            <button id="nameEditButton" type="submit" class="btn btn-warning">
                <strong>이름 수정</strong></button>
        </form:form>

        <form:form method="post" modelAttribute="user" action="/user/numberEdit" autocomplete="off">
            <div class="form-group">
                <label for="userNumber">학번</label>
                <form:input path="userNumber" type="text" class="form-control" id="userNumber" name="userNumber" value="${userNumber}" placeholder="학번을 입력해주세요." onblur="checkUserNumber()"/>
                <form:errors path="userNumber" id="userNumberError"/>
                <span id="userNumberMessage"></span>
            </div>

            <input type="hidden" name="userId" value="${loggedUser.userId}"/>
            <button id="numberEditButton" type="submit" class="btn btn-warning">
                <strong>학번 수정</strong></button>
        </form:form>

        <form:form method="post" modelAttribute="user" action="/user/emailEdit" autocomplete="off">
            <div class="form-group">
                <label for="userEmail">이메일</label>
                <form:input path="userEmail" type="text" class="form-control" id="userEmail" name="userEmail" value="${userEmail}" placeholder="이메일을 입력해주세요." onblur="checkUserEmail()"/>
                <form:errors path="userEmail" id="userEmailError"/>
                <span id="userEmailMessage"></span>
            </div>

            <input type="hidden" name="userId" value="${loggedUser.userId}"/>
            <button id="emailEditButton" type="submit" class="btn btn-warning">
                <strong>이메일 수정</strong></button>
        </form:form>

        <br/>

        <button id="editCancelButton" type="button" class="btn btn-default" onclick="location.href='/user/info'">
            <strong>취소</strong></button>

    </fieldset>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>