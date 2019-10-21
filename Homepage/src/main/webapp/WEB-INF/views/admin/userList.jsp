<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/admin/userList.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 계정 목록</title>
</head>

<div id="userList" class="container">
    <div id="userListHeader" class="container">
        <h4>계정 목록</h4>
        <p>계정 목록</p>
    </div>

    <br/>

    <div id="postListBody" class="container">
        <table class="table table-hover">
            <thead>
            <tr id="postListItemHeader">
                <th id="userId" style="width: 12.5%"><strong>계정 ID</strong></th>
                <th id="userNumber" style="width: 12.5%"><strong>계정 학번</strong></th>
                <th id="userName" style="width: 12.5%"><strong>계정 이름</strong></th>
                <th id="userEmail" style="width: 12.5%"><strong>계정 이메일</strong></th>
                <th id="userRole" style="width: 12.5%"><strong>계정 권한</strong></th>
                <th id="userIsEnabled" style="width: 12.5%"><strong>계정 인증</strong></th>
                <th id="userIsDeleted" style="width: 12.5%"><strong>계정 삭제</strong></th>
                <th style="width: 12.5%"><strong>계정 관리</strong></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userNumber}</td>
                    <td>${user.userName}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.userRole}</td>
                    <c:if test="${user.userIsEnabled eq true}"><td>활성</td></c:if>
                    <c:if test="${user.userIsEnabled eq false}"><td>비활성</td></c:if>
                    <c:if test="${user.userIsDeleted eq true}"><td>탈퇴</td></c:if>
                    <c:if test="${user.userIsDeleted eq false}"><td>활동</td></c:if>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="location.href='/admin/userInfo?userId=${user.userId}'">상세</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%-- 페이징 영역 --%>

<div id="userListPageNation" class="container">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">

            <spring:url var="previousPageUrl" value="${baseUrl}?page=${pageMaker.startPage - 1}"/>
            <c:if test="${pageMaker.previous}">
                <li class="page-item">
                    <a class="page-link" aria-label="Previous" href="${previousPageUrl}">
                        <span aria-hidden="true"><i class="fa fa-angle-double-left" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNumber">
                <spring:url var="pageUrl" value="${baseUrl}?page=${pageNumber}"/>
                <c:choose>
                    <c:when test="${currentPage eq pageNumber}">
                        <li class="page-item active">
                            <a class="page-link" href="${pageUrl}">${pageNumber}</a>
                        </li>
                    </c:when>
                    <c:when test="${currentPage ne pageNumber}">
                        <li class="page-item">
                            <a class="page-link" href="${pageUrl}">${pageNumber}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>

            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                <li class="page-item">
                    <spring:url var="nextPageUrl" value="${baseUrl}?page=${pageMaker.endPage + 1}"/>
                    <a class="page-link" aria-label="Next" href=${nextPageUrl}>
                        <span aria-hidden="true"><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<%-- 검색 폼 영역 --%>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>