<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <link href="/css/index.css" rel="stylesheet">
    <script type="text/javascript" src="/js/index.js?v=<%=System.currentTimeMillis() %>"></script>
    <title>명지대학교 융합소프트웨어학부</title>
</head>

<body>
    <div class="index">
        <br/><br/><br/>

        <!-- Carousel -->
        <div class="bs-example">
            <div id="indexCarousel" class="carousel slide" data-ride="carousel">

                <!-- Carousel indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#indexCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#indexCarousel" data-slide-to="1"></li>
                    <li data-target="#indexCarousel" data-slide-to="2"></li>
                    <li data-target="#indexCarousel" data-slide-to="3"></li>
                </ol>

                <!-- Wrapper for carousel items -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img id="img1" src="/img/carousel_1.jpg" alt="First Slide">
                    </div>
                    <div class="item">
                        <img id="img2" src="/img/carousel_2.jpg" alt="Second Slide">
                    </div>
                    <div class="item">
                        <img id="img3" src="/img/carousel_3.jpg" alt="Third Slide">
                    </div>
                    <div class="item">
                        <img id="img4" src="/img/carousel_4.jpg" alt="Fourth Slide">
                    </div>
                </div>

                <!-- Carousel controls -->
                <a class="carousel-control left" href="#indexCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="carousel-control right" href="#indexCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>

        <!-- Index Menu -->
        <table id="quick" align="center">
            <tr class="first">
                <th>공지사항</th>
                <th>학사일정</th>
                <th>대여시스템</th>
                <th>문의사항</th>
            </tr>
            <tr class="second">
                <td><img alt="공지사항" src="/img/news.jpg"/></td>
                <td><img alt="학사일정" src="/img/news.jpg"/></td>
                <td><img alt="대여시스템" src="/img/news.jpg"/></td>
                <td><img alt="문의사항" src="/img/news.jpg"/></td>
            </tr>
            <tr class="third">
                <td>강의개선설문조사</td>
                <td>0422기말고사</td>
                <td>사물함신청</td>
            </tr>
        </table>

        <!-- Top Button -->
        <p class="topButton">
            <button id="pageup" type="button" onclick="goTop()">
                <img alt="맨위로" src="/img/up_icon.png"/>
            </button>
        </p>
    </div>

    <script type="text/javascript">
        $(function() {
            var msg = "<c:out value="${msg}" />";
            if (msg != "") {
                alert(msg)
            }
        });
    </script>

</body>

</html>