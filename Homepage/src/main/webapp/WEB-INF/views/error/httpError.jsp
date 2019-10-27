<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="/resources/css/error.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 오류</title>
</head>

<body>
    <div class="jumbotron" id="error_jumbotron">
        <h2 class="display-4"><strong><c:out value='${code}'/>. That's an Error.</strong></h2>
        <p class="lead"><c:out value='${msg}'/></p>
        <hr class="my-5">
        <p>지속 발생 시, 관리자에게 문의해주세요.</p>
        <a class="btn btn-info" href="/" role="button">Home</a>
    </div>
</body>