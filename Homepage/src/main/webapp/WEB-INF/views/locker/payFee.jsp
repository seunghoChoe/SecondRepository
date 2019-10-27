<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 50%; /* Could be more or less, depending on screen size */
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
	pay

	<div class="modal" id="modal-bg">
		<div class="modal-content">
			<span class="close">&times;</span>
			<p>총 결제 금액 : ${fee}</p>
			<p>정말 결제 하시겠습니까?</p>
			<input id="confirm" type="button" value="확인"> <input
				id="cancel" type="button" value="취소">
		</div>
	</div>
	<div class="modal" id="modal-complete">
		<div class="modal-content">
			<p>
				Completed!
			</p>
		</div>
	</div>

	<script type="text/javascript">
		var modal = document.getElementById('modal-bg');
		var modalComplete = document.getElementById('modal-complete');
		var confirmBtn = document.getElementById("confirm");
		var cancelBtn = document.getElementById("cancel");
		var span = document.getElementsByClassName("close")[0];
		
		// 제출 버튼 클릭 시  
		function processPay() {
			if(${fee == null}){
				window.location='/locker/lockerApplyList';
			}else {
				modal.style.display = "block";
			}
		}
		// 확인 버튼 클릭 시 
		confirmBtn.onclick = function() {
			modal.style.display = "none";
			modalComplete.style.display = "block";
		}

		// 취소 버튼 클릭 시
		cancelBtn.onclick = function() {
			window.location='/locker/lockerApplyList';
		}

		// 모달 팝업 창의 X 표시 클릭 시 
		span.onclick = function() {
			window.location='/locker/lockerApplyList';
		}

		window.onclick = function (ev) {
			if(ev.target == modalComplete){
				window.location='/locker/payFeeCompleted';
			}
		}

		processPay();
	</script>
</body>
</html>