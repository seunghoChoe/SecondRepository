<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <link href="/resources/css/event/eventList.css" rel="stylesheet">
    <script src="/resources/js/event/eventList.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>
    <title>명지대학교 융합소프트웨어학부 : 행사 목록</title>
</head>

<!-- 게시글 영역 -->
<c:forEach var="event" items="${eventList}">
    <div>
        <p>행사 이미지</p>

        <p>행사 번호: ${event.eventId}</p>
        <p>행사 작성자: ${event.eventUserId}</p>
        <p>행사 종류: ${event.eventType}</p>
        <p>행사 제목: ${event.eventTitle}</p>
        <p>행사 내용: ${event.eventContent}</p>
        <p>행사 생성 일자: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${event.eventCreatedAt}"/></p>
        <p>행사 수정 일자: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${event.eventUpdatedAt}"/></p>
        <p>행사 조회수: ${event.eventView}</p>

        <p>행사 주최: ${event.eventOrganizer}</p>
        <p>행사 기간(시작 일자): <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${event.eventStartDate}"/></p>
        <p>행사 기간(종료 일자): <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${event.eventEndDate}"/></p>
        <p>행사 장소: ${event.eventPlace}</p>
        <p>제한 인훤: ${event.eventLimitNumber}</p>
        <p>신청 인원: ${event.eventApplyNumber}</p>
        <p>행사 마감: ${event.eventIsEnabled}</p>
        <p>행사 삭제: ${event.eventIsDeleted}</p>
    </div>
    <br/><br/><br/><br/><br/>
</c:forEach>
