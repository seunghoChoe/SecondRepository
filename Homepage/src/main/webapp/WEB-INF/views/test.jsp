<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>명지대학교 융합소프트웨어학부 : 테스트</title>
<%--    <link href="/resources/css/user/security.css" rel="stylesheet">--%>
<%--    <script src="/resources/js/user/security.js?v=<%=System.currentTimeMillis() %>" type="text/javascript"></script>--%>
</head>

<div>
    <form id="frm" action="#">
        <p>날짜 입력:</p>
        <div><input type="date" id="userdate" name="userdate" value="2015-10-10"></div>
        <div><input type="submit" value="전송"></div>
    </form>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>