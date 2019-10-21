<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="/resources/css/info.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 회원 정보</title>
</head>

<div class="container" id="user_detail_jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <i class="far fa-user fa-6x"></i>
            </div>
            <c:if test="${user.userRole == 'ADMIN'}">
                <h2>관리자 정보</h2>
            </c:if>
            <c:if test="${user.userRole == 'USER'}">
                <h2>회원 정보</h2>
            </c:if>
        </div>
        <br>
        <hr/>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <th>계정 ID</th>
                <td>${user.userId}</td>
                <th>사용자 학번</th>
                <td>${user.userNumber}</td>
            </tr>
            <tr>
                <th>사용자 이름</th>
                <td>${user.userName}</td>
                <th>사용자 이메일</th>
                <td>${user.userEmail}</td>
            </tr>
            <tr>
                <th>계정 가입일</th>
                <td colspan='3'><fmt:formatDate value="${user.userCreatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            <tr>
                <th>최근 로그인</th>
                <td colspan='3'><fmt:formatDate value="${user.userUpdatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            </tbody>
        </table>

        <button id="editButton" type="button" class="btn btn-warning" onclick="location.href='/user/edit'">
            <strong>회원정보 수정</strong></button>
        <button id="securityButton" type="button" class="btn btn-warning" onclick="location.href='/user/security'">
            <strong>비밀번호 수정</strong></button>
        <button id="securityCancelButton" type="button" class="btn btn-default" onclick="location.href='/'">
            <strong>취소</strong></button>
    </div>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>