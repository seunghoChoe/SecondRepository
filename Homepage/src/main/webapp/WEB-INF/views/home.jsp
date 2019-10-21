<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <title>명지대학교 융합소프트웨어학부 : 홈</title>
</head>

<%--<div>--%>
<%--    <div id="homeCarousel" class="carousel slide" data-ride="carousel">--%>
<%--        <ol class="carousel-indicators">--%>
<%--            <li data-target="#homeCarousel" data-slide-to="0" class="active"></li>--%>
<%--            <li data-target="#homeCarousel" data-slide-to="1"></li>--%>
<%--            <li data-target="#homeCarousel" data-slide-to="2"></li>--%>
<%--        </ol>--%>
<%--        <div class="carousel-inner" role="listbox">--%>
<%--            <div class="carousel-item">--%>
<%--                <img class="d-block" src="/resources/image/Carousel_1.png" alt="First slide">--%>
<%--                <div class="carousel-caption d-none d-md-block">--%>
<%--                    <h5>MJU ICT, Team Project</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="carousel-item active">--%>
<%--                <img class="d-block" src="/resources/image/Carousel_2.png" alt="Second slide">--%>
<%--                <div class="carousel-caption d-none d-md-block">--%>
<%--                    <h5>MJU ICT, Team Project</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="carousel-item">--%>
<%--                <img class="d-block" src="/resources/image/Carousel_3.png" alt="Third slide">--%>
<%--                <div class="carousel-caption d-none d-md-block">--%>
<%--                    <h5>MJU ICT, Team Project</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <a class="carousel-control-prev" href="#homeCarousel" role="button" data-slide="prev">--%>
<%--            <i class="now-ui-icons arrows-1_minimal-left"></i>--%>
<%--        </a>--%>
<%--        <a class="carousel-control-next" href="#homeCarousel" role="button" data-slide="next">--%>
<%--            <i class="now-ui-icons arrows-1_minimal-right"></i>--%>
<%--        </a>--%>
<%--    </div>--%>
<%--</div>--%>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>