<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"	href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"	href="https://www.w3schools.com/lib/w3-theme-lime.css">
<link rel="stylesheet"	href="https://fonts.googleapis.com/css?family=Allerta+Stencil">
<script src="https://kit.fontawesome.com/dff711313e.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
	font-family: "Allerta Stencil";
}
/* The Close Button */
.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<!-- Side Bar  -->
	<div class="w3-sidebar w3-bar-block w3-cell w3-light-green" style="width: 17%;">
		<a href="/locker/index" class="w3-bar-item w3-large w3-center">
			<i	class="fas fa-box-open fa-5x w3-text-white"></i>
		</a>
		
		<div class="w3-bar w3-green w3-theme-l3" style="height:1px;"></div>
		<a href="#" class="w3-bar-item w3-text-white w3-theme-d2 w3-xlarge" style="text-decoration:none;">Menu</a>
		<div class="w3-bar w3-green w3-theme-l3" style="height:1px;"></div>
			<a href="/locker/lockerList" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함	현황 확인</a>
			<a href="/locker/lockerApplyList" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함 신청</a>
			
		<div class="w3-bar w3-green w3-theme-l3" style="height:1px;"></div>
		<a href="#" class="w3-bar-item w3-text-white w3-theme-d2 w3-xlarge" style="text-decoration:none;">My Locker</a>
		<div class="w3-bar w3-green w3-theme-l3" style="height:1px;"></div>
			<a href="/locker/lockerExtend" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함 연장</a>
			<a href="#" class="w3-bar-item w3-button w3-text-white w3-hover-lime w3-large">사물함 반납</a>
		
	</div>

	
	<div class="w3-container" style="margin-left:15%;">
	
		<!-- Header -->
		<div class="w3-container w3-lime w3-padding-large" style="height:160px">
			<h1 class="w3-xxxlarge" style="font-family: Allerta Stencil;text-align:center;">
				Locker Apply &nbsp;&nbsp;  
				<i class="fab fa-apple fa-2x"></i>	
			</h1>
		</div>
		
		<!-- Table Space -->
		<div class="w3-container w3-large" style="margin: 5% 5% 5% 5%;height:60%;padding:5%">
			<table class="w3-table w3-bordered w3-centered w3-xlarge">
				<tr>
					<th>LockerID</th>
					<th>State</th>
					<th>FinishDate</th>
					<th></th>
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
						<td>
							<c:if test="${locker.state == 0}">
								<button class="w3-btn w3-round w3-tiny w3-green w3-hover-light-gray"
								onclick="apply(${locker.id})">Apply</button>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
			<div class="w3-container" style="height:30px">
				<c:if test="${failMessage != null}">
					<div id="failMessage" class="w3-panel w3-pale-red w3-xlarge w3-center w3-animate-zoom" style="width: 60%;">
						<span class="close" id="failClose">&times;</span>
						<p class="w3-text-gray">${failMessage}</p>
					</div>
				</c:if>
				<c:if test="${successMessage != null}">
					<div id="successMessage" class="w3-panel w3-pale-green w3-xlarge w3-center w3-animate-zoom" style="width: 60%;">
						<span class="close" id="successClose">&times;</span>
						<p class="w3-text-black">DEAR ${loggedUser.userId} </p>
						<p class="w3-text-gray">${successMessage}</p>
					</div>
				</c:if>
			</div>
			<div class="w3-container">
				<a class="w3-btn w3-round w3-theme-d2 w3-right" style="margin-top:2%" href="/locker/index"> 돌아가기 </a>
			</div>
		</div>
		
		<!-- Footer -->
		<div class="w3-container w3-theme-d2 w3-xxlarge w3-center" style="margin-bottom:0;">
			<div style="margin-left: 4%">
				<h6>LockerManagement System</h6>
			</div>
		</div>
	</div>
	
	
<!-- Modal 창  -->	
<div id="modal" class="w3-modal">
	<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="width:40%">
		<div class="w3-container w3-theme-d1">
			<h2>Apply Form</h2>
			<span onclick="document.getElementById('modal').style.display='none'" class="w3-button w3-display-topright">&times;</span>
		</div>
		<form class="w3-container" action="/locker/LockerApplyForm" method="post" name="lockerApply" id="lockerApply">
				<p>
					<input id="lockerId" class="w3-input" type="text" name="id" readonly>
					<label>LockerID</label>
				</p>
				<p>
					<input class="w3-input" type="text" value="${loggedUser.userId}" name="userId" readonly>
					userID
				</p>
				<p>
					<input class="w3-input" type="date" name="startDate">
					<label>StartDate</label>
				</p>
				<p>
					<input class="w3-input" type="date" name="finishDate">
					<label>FinishDate</label> 
				</p>
				<p>
					<input class="w3-btn w3-theme-l2 w3-round w3-right" type="submit" value="Apply" style="margin:2% 1%">
				</p>
		</form>
	</div>
</div>
	
<script>
	var failMessage = document.getElementById("failMessage");
	var successMessage = document.getElementById("successMessage");
	var failClose = document.getElementById("failClose");
	var successClose = document.getElementById("successClose");

	window.onclick = function(event) {
		if(event.target == failClose) {
			failMessage.style.display = "none";
		}else if(event.target == successClose){
			successMessage.style.display ="none";
		}
	}
	function apply(lockerId) {
		document.getElementById('modal').style.display = 'block';
		var inputLockerId = document.getElementById('lockerId');
		inputLockerId.setAttribute('value', lockerId)
	}
</script>
</body>
</html>