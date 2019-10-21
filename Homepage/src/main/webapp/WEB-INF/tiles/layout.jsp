<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- Core CSS Files --%>
    <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/assets/css/now-ui-kit.css?v=1.2.0" rel="stylesheet"/>
    <link href="/resources/assets/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
    <%-- Custom CSS Files --%>
    <link href="/resources/css/common.css" rel="stylesheet"/>
    <link href="/resources/css/include/content.css" rel="stylesheet"/>
    <%-- Icon and Fonts--%>
    <link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900&amp;subset=latin-ext" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500&display=swap&subset=korean" rel="stylesheet"/>
    <%-- Core JS Files --%>
    <script src="/resources/assets/js/core/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/assets/js/core/popper.min.js" type="text/javascript"></script>
    <script src="/resources/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js" type="text/javascript"></script>
    <%-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc --%>
    <script src="/resources/assets/js/now-ui-kit.js?v=1.2.0" type="text/javascript"></script>
    <%-- PlugIn for the DateTimePicker, full documentation here: https://www.malot.fr/bootstrap-datetimepicker --%>
    <script src="/resources/assets/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
</head>

<body>
<div class="wrapper" style="margin: 0 auto">

    <%-- Header영역 --%>
    <div class="layoutHeader">
        <tiles:insertAttribute name="header" ignore="true"/>
    </div>

    <%-- Menu 영역 --%>
    <div class="layoutMenu">
        <tiles:insertAttribute name="menu" ignore="true"/>
    </div>

    <%-- Content 영역 --%>
    <div class="layoutContent">
        <tiles:insertAttribute name="content" ignore="true"/>
    </div>

    <%-- Footer 영역 --%>
    <div class="layoutFooter">
        <tiles:insertAttribute name="footer" ignore="true"/>
    </div>
</div>

</body>

</html>