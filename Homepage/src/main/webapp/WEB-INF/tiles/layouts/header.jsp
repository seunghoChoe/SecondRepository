<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link href="/resources/css/layouts/header.css" rel="stylesheet">
</head>

<body>

    <!-- Navigation bar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="http://www.mju.ac.kr" target="_blank">
                    <img class="mju-logo" src="/resources/img/mju_logo.png" alt="명지대학교">
                </a>
                <%--<span>--%>
                    <%--<a href="http://www.mju.ac.kr" target="_blank">--%>
                        <%--<img class="mju-logo" src="/resources/img/mju_logo.png" alt="명지대학교">--%>
                    <%--</a>--%>
                <%--</span>--%>
                <%--<a class="navbar-brand" href="http://www.mju.ac.kr" target="_blank"><img class="mju-logo" src="/resources/img/mju_logo.png" alt="명지대학교"></a>--%>

                <span class="sw">
                    Convergence Software
                </span>
            </div>

            <div class="navbar-collapse collapse" id="navbar">
                <!-- Top menu -->
                <ul class="nav navbar-nav navbar-right" id="top_menu">
                    <li><a href="/">Home</a></li>
                    <c:if test="${loggedUser == null}">
                        <li><a href="/user/loginForm">Login</a></li>
                        <li><a href="/user/joinForm">Join us</a></li>
                    </c:if>
                    <c:if test="${loggedUser != null}">
                        <c:if test="${loggedUser.userAuthority == 'ADMIN'}">
                            <li><a href="/admin/index">Admin</a></li>
                        </c:if>
                        <li><a href="/user/userDetail">My Info</a></li>
                        <li><a href="/user/logout">Logout</a></li>
                    </c:if>
                    <li><a href="#">Site Map</a></li>
                </ul>
            </div>
        </div>
    </nav>
</body>