<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="/resources/css/userForm.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/loginForm.js?v=<%=System.currentTimeMillis() %>"></script>
    <title>명지대학교 융합소프트웨어학부 : 회원정보 수정</title>
</head>

<body>
    <div class="jumbotron" id="modify_info_jumbotron">

        <div class="form-group">
            <p><i class="fas fa-user-circle fa-6x"></i></p>
            <h3 class="form-group-heading">회원정보 수정</h3>
            <p class="form-group-paragraph">변경할 회원정보를 입력하세요.</p>
        </div>

        <fieldset>
            <legend><h1>회원정보 수정</h1></legend>
            <c:url var="modifyInfoPath" value="/user/modifyInfo"/><br>
            <form:form class="modifyInfoForm" id="frm" method="post" action="${modifyInfoPath}" modelAttribute="modifyInfoForm" autocomplete="off">

                <div class="form-group">
                    <label for="userId">ID</label>
                    <form:input path="userId" type="text" class="form-control" id="userId" name="userId" placeholder="ID를 입력해주세요." onblur="checkId()"/>
                    <form:errors path="userId" id="errorId"/>
                    <span id="showIdMsg"></span>
                </div>

                <div class="form-group">
                    <label for="userName">Name</label>
                    <form:input path="userName" type="text" class="form-control" id="userName" name="userName" value="${userName}" placeholder="이름을 입력해주세요." onblur="checkName()"/>
                    <form:errors path="userName" id="errorName"/>
                    <span id="showNameMsg"></span>
                </div>

                <div class="form-group">
                    <label for="userEmail">Email</label>
                    <form:input path="userEmail" type="text" class="form-control" id="userEmail" name="userEmail" value="${userEmail}" placeholder="이메일을 입력해주세요." onblur="checkEmail()"/>
                    <form:errors path="userEmail" id="errorEmail"/>
                    <span id="showEmailMsg"></span>
                </div>

                <div class="form-group">
                    <label for="userSno">StudentNumber</label>
                    <form:input path="userSno" type="text" class="form-control" id="userSno" name="userSno" value="${userSno}" placeholder="학번을 입력해주세요." oninput="checkSno()"/>
                    <form:errors path="userSno" id="errorSno"/>
                    <span id="showSnoMsg"></span>
                </div>

                <div class="btn-group">
                    <p>
                        <input type=submit class="btn btn-dark" value="회원정보 수정">
                        <input type=button class="btn btn-dark" value="돌아가기" onclick="window.location='/user/userDetail'">
                    </p>
                </div>
            </form:form>
        </fieldset>
    </div>
</body>