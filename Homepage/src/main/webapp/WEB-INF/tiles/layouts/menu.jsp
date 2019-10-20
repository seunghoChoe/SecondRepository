<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link href="/resources/css/layouts/menu.css" rel="stylesheet">
</head>

<body>
    <div class="menu">
        <!-- Tel & Email icon -->
        <div class="icon">
            <img id="tel" alt="전화연결" src="/resources/img/phone_icon.png" onclick=alert("02-300-0643")>
            <img id="email" alt="이메일" src="/resources/img/email_icon.png" onclick=alert("http://mju.ac.kr")>
        </div>

        <!-- Dropdown list -->
        <ul>
            <li class="dropdown">
                <a href="#" class="dropbtn">ABOUT</a>
                <div class="dropdown-content">
                    <a href="#">학부 소개</a>
                    <a href="#">교수진 소개</a>
                    <a href="#">교육 과정</a>
                    <a href="#">학회 안내</a>
                    <a href="#">진로 및 전망</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="#" class="dropbtn">공지사항</a>
                <div class="dropdown-content">
                    <a href="#">일반 공지</a>
                    <a href="#">학사 일정</a>
                    <a href="#">취업 공지</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="#" class="dropbtn">학생광장</a>
                <div class="dropdown-content">
                    <a href="/board/boardList">자유 게시판</a>
                    <a href="#">질문 게시판</a>
                    <a href="#">사진 게시판</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="#" class="dropbtn">대여서비스</a>
                <div class="dropdown-content">
                    <a href="/locker/index">사물함 대여</a>
                    <a href="#">노트북 대여</a>
                </div>
            </li>
        </ul>

        <div class="col-md-4 col-md-offset-3" id="search-bar">
            <form action="" class="search-form">
                <div class="form-group has-feedback">
                    <label for="search" class="sr-only">Search</label>
                    <input type="text" class="form-control" name="search" id="search" placeholder="search..!">
                    <span class="glyphicon glyphicon-search form-control-feedback"></span>
                </div>
            </form>
        </div>
    </div>
</body>