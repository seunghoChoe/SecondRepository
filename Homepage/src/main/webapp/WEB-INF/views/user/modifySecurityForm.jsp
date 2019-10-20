<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="/resources/css/userForm.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/joinForm.js?v=<%=System.currentTimeMillis() %>"></script>
    <title>명지대학교 융합소프트웨어학부 : 비밀번호 수정</title>
</head>

<body>
    <div class="jumbotron" id="modify_security_jumbotron">

        <div class="form-group">
            <p><i class="fas fa-user-circle fa-6x"></i></p>
            <h3 class="form-group-heading">비밀번호 수정</h3>
            <p class="form-group-paragraph">변경할 비밀번호를 입력하세요.</p>
        </div>

        <fieldset>
            <legend><h1>비밀번호 수정</h1></legend>
            <c:url var="modifySecurityPath" value="/user/modifySecurity"/>
            <form:form class="modifyInfoForm" id="frm" method="post" action="${modifySecurityPath}" modelAttribute="modifySecurityForm" autocomplete="off">

                <div class="form-group">
                    <label for="userPw">새 비밀번호 입력</label>
                    <form:input path="userPw" type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호를 입력해주세요." onblur="checkPw()"/>
                    <form:errors path="userPw" id="errorPw"/>
                </div>

                <div class="form-group">
                    <label for="confirmPw">새 비밀번호 확인</label>
                    <input type="password" class="form-control" id="confirmPw" placeholder="비밀번호를 확인해주세요." onblur="checkPw()"/>
                    <span id="showPwMsg"></span>
                </div>

                <div class="btn-group">
                    <p>
                        <input type=submit class="btn btn-dark" value="비밀번호 수정">
                        <input type=button class="btn btn-dark" value="돌아가기" onclick="window.location='/user/userDetail'">
                    </p>
                </div>
            </form:form>
        </fieldset>
    </div>
</body>