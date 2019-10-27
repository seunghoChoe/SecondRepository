<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="/resources/css/userDetail.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 회원정보</title>
</head>

<body>
    <div class="jumbotron" id="user_detail_jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <i class="far fa-user fa-6x"></i>
                </div>
                <c:if test="${selectedUser.userAuthority == 'ADMIN'}">
                    <h2>관리자 정보</h2>
                </c:if>
                <c:if test="${selectedUser.userAuthority != 'ADMIN'}">
                    <h2>회원 정보</h2>
                </c:if>
            </div>
            <br>
            <hr/>
            <table class="table table-bordered">
                <tbody>
                <tr>
                    <th>계정 ID</th>
                    <td>${selectedUser.userId}</td>
                    <th>사용자 학번</th>
                    <td>${selectedUser.userSno}</td>
                </tr>
                <tr>
                    <th>사용자 이름</th>
                    <td>${selectedUser.userName}</td>
                    <th>사용자 이메일</th>
                    <td>${selectedUser.userEmail}</td>
                </tr>
                <tr>
                    <th>계정 가입일</th>
                    <td colspan='3'><fmt:formatDate value="${selectedUser.userRegDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <th>최근 로그인</th>
                    <td colspan='3'><fmt:formatDate value="${selectedUser.userLogDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                </tbody>
            </table>

            <div class="btn-group">
                <p>
                    <input type=button class="btn btn-primary" value="회원정보 수정" onclick="window.location='/user/modifyInfoForm'">
                    <input type=button class="btn btn-primary" value="비밀번호 수정" onclick="window.location='/user/modifySecurityForm'">
                    <input type=button class="btn btn-primary" value="홈" onclick="window.location='/'">
                </p>
            </div>
        </div>
    </div>
</body>