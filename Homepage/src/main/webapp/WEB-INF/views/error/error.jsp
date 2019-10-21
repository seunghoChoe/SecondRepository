<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="/resources/css/error/error.css" rel="stylesheet">
</head>

<br/>
<div id="error" class="container">
    <h1><strong>${errorCode}</strong></h1>
    <h2><strong>${errorMessage}</strong></h2>
    <br/>
    <p>입력하신 페이지 주소가 정확한지 다시 한번 확인해보시기 바랍니다.</p>
    <p>관련하여 문의 사항이 있으시면 언제든지 관리자에게 문의해 주시기 바랍니다.</p>
    <br/>
    <a id="errorHomeButton" class="btn btn-warning" href="/" role="button"><i class="fas fa-home"></i> 홈 화면</a>
</div>
<br/>