<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="/resources/css/userForm.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/joinForm.js?v=<%=System.currentTimeMillis() %>" ></script>
    <title>명지대학교 융합소프트웨어학부 : 회원가입</title>
</head>

<body>
    <div class="jumbotron" id="join_jumbotron">
        <h1>회원가입</h1>
        <p><i class="fas fa-user-circle fa-6x"></i></p>

        <c:url var="joinPath" value="/user/join"/>
        <form:form class="joinForm" method="post" action="${joinPath}" modelAttribute="joinForm" autocomplete="off">
            <div class="paragraph-group">
                <h3 class="form-group-heading">환영합니다.</h3>
                <h3><small>가입 정보를 입력해주세요.</small></h3>
            </div>
            <br>

            <div class="form-group">
                <label for="userId">ID</label>
                <form:input path="userId" type="text" class="form-control" id="userId" name="userId" placeholder="ID를 입력해주세요." onblur="checkId()"/>
                <form:errors path="userId" id="errorId"/>
                <span id="showIdMsg"></span>
            </div>

            <div class="form-group">
                <label for="userPw">비밀번호</label>
                <form:input path="userPw" type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호를 입력해주세요." onblur="checkPw()"/>
                <form:errors path="userPw" id="errorPw"/>
            </div>

            <div class="form-group">
                <label for="confirmPw">비밀번호 확인</label>
                <input type="password" class="form-control" id="confirmPw" placeholder="비밀번호를 확인해주세요." onblur="checkPw()"/>
                <span id="showPwMsg"></span>
            </div>

            <div class="form-group">
                <label for="userName">이름</label>
                <form:input path="userName" type="text" class="form-control" id="userName" name="userName" placeholder="이름을 입력해주세요." onblur="checkName()"/>
                <form:errors path="userName" id="errorName"/>
                <span id="showNameMsg"></span>
            </div>

            <div class="form-group">
                <label for="userSno">학번</label>
                <form:input path="userSno" type="text" class="form-control" id="userSno" name="userSno" placeholder="학번을 입력해주세요." onblur="checkSno()"/>
                <form:errors path="userSno" id="errorSno"/>
                <span id="showSnoMsg"></span>
            </div>

            <div class="form-group">
                <label for="userEmail">이메일</label>
                <form:input path="userEmail" type="email" class="form-control" id="userEmail" name="userEmail" placeholder="이메일을 입력해주세요." onblur="checkEmail()"/>
                <form:errors path="userEmail" id="errorEmail"/>
                <span id="showEmailMsg"></span>
            </div>

            <div class="btn-group">
                <p>
                    <button type="submit" class="btn btn-default" id="joinBtn">회원가입</button>
                    <button type="button" class="btn btn-default" id="indexBtn" onclick="window.location='/'">홈</button>
                </p>
            </div>
        </form:form>

    </div>
</body>