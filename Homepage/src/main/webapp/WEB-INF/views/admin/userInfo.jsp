<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/admin/userInfo.css" rel="stylesheet">
    <script src="/resources/js/admin/userInfo.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <title>명지대학교 융합소프트웨어학부 : 계정 관리</title>
</head>

<div class="container">
    <p>계정 ID: ${user.userId}</p>
    <p>사용자 학번: ${user.userNumber}</p>
    <p>사용자 이름: ${user.userName}</p>
    <p>사용자 이메일: ${user.userEmail}</p>
    <p>계정 접근 권한: ${user.userRole}</p>
    <p>계정 가입 일자: <fmt:formatDate value="${user.userCreatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
    <p>최근 로그인 일자: <fmt:formatDate value="${user.userUpdatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
    <c:if test="${user.userIsEnabled eq true}"><p>계정 승인 여부: 활성</p></c:if>
    <c:if test="${user.userIsEnabled eq false}"><p>계정 승인 여부: 비활성</p></c:if>
    <c:if test="${user.userIsDeleted eq true}"><p>계정 삭제 여부: 탈퇴</p></c:if>
    <c:if test="${user.userIsDeleted eq false}"><p>계정 삭제 여부: 활동중</p></c:if>
</div>

<c:if test="${user.userIsEnabled eq true}">
    <button class="btn btn-danger" type="button" onclick="userActiveEdit('${user.userId}', false)">계정 비활성화</button>
</c:if>
<c:if test="${user.userIsEnabled eq false}">
    <button class="btn btn-warning" type="button" onclick="userActiveEdit('${user.userId}', true)">계정 활성화</button>
</c:if>
<c:if test="${user.userIsDeleted eq false}">
    <button class="btn btn-danger" type="button" onclick="userDeleteEdit('${user.userId}', true)">계정 삭제</button>
</c:if>
<c:if test="${user.userIsDeleted eq true}">
    <button class="btn btn-warning" type="button" onclick="userDeleteEdit('${user.userId}', false)">계정 복구</button>
</c:if>
<button class="btn btn-default" type="button" onclick="location.href='/admin/userList'">취소</button>