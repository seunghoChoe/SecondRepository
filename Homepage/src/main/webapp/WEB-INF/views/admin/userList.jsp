<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link href="/resources/css/userList.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 회원 목록</title>
</head>

<body>
    <div class="jumbotron" id="userListJumbotron">

        <c:if test="${msg != null}">
            <br>
            <div class="alert alert-info">
                <strong>${msg}</strong>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <div class="view overlay my-4">
            <i class="fas fa-address-card fa-5x"></i>
            <br>
            <br>
            <h3 class="display-5"><strong>홈페이지 회원 목록</strong></h3>
            <br>
        </div>

        <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th class="th-sm"><strong>계정 ID</strong>
                </th>
                <th class="th-sm"><strong>계정 학번</strong>
                </th>
                <th class="th-sm"><strong>계정 이름</strong>
                </th>
                <th class="th-sm"><strong>계정 이메일</strong>
                </th>
                <th class="th-sm"><strong>계정 권한</strong>
                </th>
                <th class="th-sm"><strong>계정 인증</strong>
                </th>
                <th class="th-sm"><strong>계정 관리</strong>
                </th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userSno}</td>
                    <td>${user.userName}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.userAuthority}</td>
                      <td>
                     <c:if test="${user.userEnabled == 1}">
                		<div>인증</div>
	            	</c:if>
	           		<c:if test="${user.userEnabled == 0}">
	           			<div>미인증</div>
	                </c:if>
                   </td>
                    <td>
                        <spring:url value="/admin/userDetail/${user.userId}" var="showUserPath" />
                        <button class="btn btn-info btn-sm" onclick="location.href='${showUserPath}'">상세</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

            <tfoot>
            <tr>
                <th class="th-sm"><strong>계정 ID</strong>
                </th>
                <th class="th-sm"><strong>계정 학번</strong>
                </th>
                <th class="th-sm"><strong>계정 이름</strong>
                </th>
                <th class="th-sm"><strong>계정 이메일</strong>
                </th>
                <th class="th-sm"><strong>계정 권한</strong>
                </th>
                <th class="th-sm"><strong>계정 인증</strong>
                </th>
                <th class="th-sm"><strong>계정 관리</strong>
                </th>
            </tr>
            </tfoot>
        </table>
    </div>

</body>