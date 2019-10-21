<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <link href="/resources/css/event/newEvent.css" rel="stylesheet">
    <script src="/resources/ckeditor4/ckeditor.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <script src="/resources/js/event/newEvent.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <title>명지대학교 융합소프트웨어학부 : 행사 등록</title>
</head>

<!-- 게시글 영역 -->
<div id="newEventForm" class="container">
    <form:form method="post" modelAttribute="event" action="/event/newEvent" autocomplete="off">
        <div class="form-group">
            <label for="eventTitle">행사 제목</label>
            <form:input id="eventTitle" class="form-control" name="eventTitle" path="eventTitle" type="text" placeholder="행사 제목: 1~100자 입력"/>
            <form:errors path="eventTitle" id="errorEventTitle"/>
        </div>

        <div class="form-group">
            <label for="eventOrganizer">행사 주최사</label>
            <form:input id="eventOrganizer" class="form-control" name="eventOrganizer" path="eventOrganizer" type="text" placeholder="행사 주최사: 1~50자 입력"/>
            <form:errors path="eventOrganizer" id="errorEventOrganizer"/>
        </div>

        <div class="form-group">
            <label for="eventContact">행사 주최사 연락처</label>
            <form:input id="eventContact" class="form-control" name="eventContact" path="eventContact" type="text" placeholder="행사 주최사 연락처: 1~50자 입력"/>
            <form:errors path="eventContact" id="errorEventContact"/>
        </div>

        <div class="form-group">
            <label for="eventPlace">행사 장소</label>
            <form:input id="eventPlace" class="form-control" name="eventPlace" path="eventPlace" type="text" placeholder="행사 장소: 1~50자 입력"/>
            <form:errors path="eventPlace" id="errorEventPlace"/>
        </div>

        <div class="form-group">
            <label for="eventLimitNumber">행사 제한 인원</label>
            <form:input id="eventLimitNumber" class="form-control" name="eventLimitNumber" path="eventLimitNumber" type="digit" placeholder="행사 제한 인원 입력"/>
            <form:errors path="eventLimitNumber" id="errorEventLimitNumber"/>
        </div>

        <div class="form-group">
            <label for="eventType">행사 종류</label>
            <form:radiobutton path="eventType" value="타입1" />타입1
            <form:radiobutton path="eventType" value="타입2" />타입2
            <form:errors path="eventType"/>
        </div>

        <div class="form-group">
            <label for="eventStartDate">행사 시작일</label>
<%--            <fmt:formatDate value="${event.eventStartDate}" var="eventStartDateString" pattern="YYYY-MM-DD hh:mm:ss" />--%>
<%--            <form:input id="eventStartDate" class="form-control" name="eventStartDate" path="eventStartDate" type="text" value="${eventStartDateString}"/>--%>
            <form:input id="eventStartDate" class="form-control" name="eventStartDate" path="eventStartDate" type="text"/>
            <form:errors path="eventStartDate" id="eventStartDate"/>
        </div>

        <div class="form-group">
            <label for="eventEndDate">행사 종료일</label>
<%--            <fmt:formatDate value="${event.eventEndDate}" var="eventEndDateString" pattern="YYYY-MM-DD hh:mm:ss" />--%>
<%--            <form:input id="eventEndDate" class="form-control" name="eventEndDate" path="eventEndDate" type="text" value="${eventEndDateString}"/>--%>
            <form:input id="eventEndDate" class="form-control" name="eventEndDate" path="eventEndDate" type="text"/>
            <form:errors path="eventEndDate" id="eventEndDate"/>
        </div>

        <br/>

        <div class="form-group">
            <label for="eventContent">행사 내용</label>
            <form:textarea id="eventContent" class="form-control" name="eventContent" path="eventContent" cols="10" placeholder="행사 내용: 1~2500자 입력"/>
            <form:errors path="eventContent" id="errorEventContent"/>
        </div>

        <br/>

        <button id="newEventButton" type="submit" class="btn btn-warning">
            <strong>등록</strong></button>
        <button id="newEventCancelButton" type="button" class="btn btn-default" onclick="location.href='/event/eventList'">
            <strong>취소</strong></button>
    </form:form>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>