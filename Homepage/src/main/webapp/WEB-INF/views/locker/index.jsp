<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<html>
<head>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-lime.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Allerta+Stencil">
	<script src="https://kit.fontawesome.com/dff711313e.js"></script>
	<title>Home</title>
<style>
body, h1{
	font-family:"Allerta Stencil";
}

.noUnderLine{
	text-decoration:none;
	font-family:Monaco;

}
.div-image{
	font-size:40px;
	text-align:center;
}

.footer{
	font-family: "Times New Roman";
}
</style>

</head>
<body>

<div class="w3-container w3-lime w3-text-white">
  <h1>Locker Management System </h1>
</div>

<div class="w3-container  w3-theme-l4">
	<div class="w3-container w3-padding-large div-image">
		<i class="fas fa-box-open fa-7x w3-theme-d4"></i>
	</div>
	<ul class="w3-ul w3-card-2 w3-xlarge w3-padding-large w3-theme-l5" style="width:100%;">
  		<li class="w3-padding-large"><a href=/locker/lockerList class="noUnderLine" >1. 사물함 현황 확인  </a></li>
  		<li class="w3-padding-large"><a href=/locker/lockerApplyList class="noUnderLine">2. 사물함 신청  </a></li>
  		<li class="w3-padding-large"><a href=/locker/LockerExtension class="noUnderLine">3. 사물함 연장  </a></li>
  		<li class="w3-padding-large"><a href=# class="noUnderLine">4. 사물함 반납  </a></li>
  		<li class="w3-padding-large"><a href="/" class="noUnderLine">5. 홈으로  </a></li>
	</ul>
</div>

<div class="w3-container w3-theme-l3 w3-center">
  <p class="footer">
  	Locker Management System<br>
  	made by SH, Now, SJ<br>
  	designed by SH, SJ<br>
  </p>
</div>



</body>
</html>
