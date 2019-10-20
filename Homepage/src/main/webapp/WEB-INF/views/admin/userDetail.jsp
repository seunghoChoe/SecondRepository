<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <link href="/resources/css/userDetail.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:700&display=swap&subset=korean" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 회원 상세 조회</title>
</head>

<body>
    <div class="jumbotron" id="user_detail_jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <i class="far fa-user fa-6x"></i>
                </div>
                <h2 class="col-sm-3"><span>${selectedUser.userId}</span>님의 회원 정보</h2>
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
                <tr>
                    <th>계정 권한</th>
                    <td>${selectedUser.userAuthority}</td>
                    <th>계정 인증</th>
                    <c:if test="${selectedUser.userEnabled == 1}">
                        <td>인증</td>
                    </c:if>
                    <c:if test="${selectedUser.userEnabled == 0}">
                        <td>미인증</td>
                    </c:if>
                </tr>
                </tbody>
            </table>

            <hr/>
            <br>

            <spring:url value="/admin/enableUser/${selectedUser.userId}" var="enableUserPath"/>
            <spring:url value="/admin/unEnableUser/${selectedUser.userId}" var="unEnableUserPath"/>
            <spring:url value="/admin/deleteUser/${selectedUser.userId}" var="deleteUserPath"/>
            <c:if test="${selectedUser.userEnabled == 0}">
                <button id="ok" class="btn btn-info" onclick="location.href='${enableUserPath}'">계정 활성화</button>
            </c:if>
            <c:if test="${selectedUser.userEnabled == 1}">
                <button id="no" class="btn btn-danger" onclick="location.href='${unEnableUserPath}'">계정 비활성화</button>
            </c:if>

            <button class="btn btn-danger" onclick="func_confirm()">계정 삭제</button>
            <button type="button" class="btn btn-primary" id="userListBtn" onclick="window.location='/admin/userList'">회원 목록</button>

            <script type="text/javascript">
                function func_confirm() {
                    if (confirm('정말 계정을 삭제하시겠습니까?')) {
                        alert("삭제되었습니다.");
                        location.href = '${deleteUserPath}';
                    } else {
                        alert("취소되었습니다.");
                    }
                }
            </script>

        </div>
    </div>
</body>