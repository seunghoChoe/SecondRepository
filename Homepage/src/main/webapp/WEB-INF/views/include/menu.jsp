<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <link href="/resources/css/include/menu.css" rel="stylesheet">
    <script src="/resources/js/include/menu.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <title>Title</title>
</head>

<div id="menu" class="btn-group">
    <div class="dropdown">
        <button id="aboutUsDropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            About Us
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">학부 소개</a>
            <a class="dropdown-item" href="#">교수진 소개</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">교육 과정</a>
            <a class="dropdown-item" href="#">학회 안내</a>
        </div>
    </div>

    <div class="dropdown">
        <button id="announcementDropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Announcement
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#"><i class="fas fa-bell"></i> 일반 공지</a>
            <a class="dropdown-item" href="#">학사 공지</a>
            <a class="dropdown-item" href="#">교육 과정</a>
        </div>
    </div>

    <div class="dropdown">
        <button id="communityDropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Community
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/board/postList">자유 게시판</a>
            <a class="dropdown-item" href="#">질문 게시판</a>
            <a class="dropdown-item" href="#">사진 게시판</a>
        </div>
    </div>

    <div class="dropdown">
        <button id="serviceDropdownMenuButton" class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Service
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/event/eventList">행사 목록</a>
            <a class="dropdown-item" href="/event/newEvent">행사 등록</a>
        </div>
    </div>
</div>