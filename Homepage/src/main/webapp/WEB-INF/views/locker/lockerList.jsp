<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-lime.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Allerta+Stencil">
	<script src="https://kit.fontawesome.com/dff711313e.js"></script>
<title>Insert title here</title>
<style>
body{
	font-family:"Allerta Stencil";
}

</style>
</head>
<body>
<div class="w3-sidebar w3-bar-block w3-cell w3-light-green" style="width:17%;">
	<a href="/locker/index" class="w3-bar-item w3-text-white w3-large w3-center">
		<i class="fas fa-box-open fa-8x"></i>
	</a>
	<a href="/locker/lockerList" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함 현황 확인</a>
	<a href="/locker/lockerApplyList" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함 신청</a>
	<a href="#" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함 연장</a>
	<a href="#" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함 반납</a>
</div>

<div class="w3-sidebar w3-bar-block w3-cell w3-light-green" style="right:0">
	<p class="w3-center w3-large w3-text-white">
		<i class="fab fa-apple fa-8x"></i>
	</p>
</div>

<div class="w3-container" style="margin-left:15%;margin-right:15%;">
	<div class="w3-container w3-lime">
		<div class="w3-container" style="margin-left:4%;">
			<h1 class="w3-xxxlarge" style="font-family:Allerta Stencil">
				Locker List
			</h1> 
		</div>
	</div>
	<div class="w3-container w3-large" style="margin: 5% 5% 5% 5%;height:60%;padding:5%">
		<table class="w3-table w3-bordered w3-centered w3-xlarge">
			<tr>
				<th>LockerID</th>
				<th>State</th>
				<th>FinishDate</th>
			</tr>
			<c:forEach items="${lockerList}" var="locker">
				<tr>
					<td>${locker.id}</td>
					<td>
						<c:if test="${locker.state == 0}">
							EMPTY
						</c:if>
						<c:if test="${locker.state == 1}">
							USING
						</c:if>
						<c:if test="${locker.state == 2}">
							BROKEN
						</c:if>
					</td>
					<td>${locker.finishDate}</td>
				</tr>
			</c:forEach>
		</table>
		<p>
			<a class="w3-btn w3-round w3-theme-d2 w3-right" style="margin-top:2%" href="/locker/index"> 돌아가기 </a>
		</p>
	</div>
		<div class="w3-container w3-theme-d2 w3-xxlarge" style="margin-bottom:0;">
			<div style="margin-left:4%">
				footer
			</div>
		</div>
	</div>



</body>
</html>