<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="/resources/css/include/header.css" rel="stylesheet">
    <title>Title</title>
</head>

<div id="header">
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
        </li>
        <c:if test="${loggedUser == null}">
            <li class="nav-item">
                <a class="nav-link" href="/user/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/join">Join</a>
            </li>
        </c:if>

        <c:if test="${loggedUser != null}">
            <c:if test="${loggedUser.userRole == 'ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link" href="/admin/home">Admin Page</a>
                </li>
            </c:if>

            <li class="nav-item">
                <a class="nav-link" href="/user/info">My Page</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/logout">Logout</a>
            </li>
        </c:if>

        <li class="nav-item">
            <a class="nav-link" href="#">Site Map</a>
        </li>
    </ul>
</div>

<div id="headerLogo">
    <a href="/">MJU ICT</a>
</div>