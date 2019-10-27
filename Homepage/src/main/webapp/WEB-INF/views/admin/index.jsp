<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <link href="/resources/css/adminIndex.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 관리자 홈</title>
</head>

<body>

    <div class="admin_index">
        <div class="shadow-none jumbotron" id="admin-jumbotron">
            <p><i class="fas fa-user-cog fa-4x"></i></p>
            <h3 class="form-group-heading"><strong>${loggedUser.userId}님, 환영합니다.</strong></h3>
            <h3><small>각 관리 페이지로 이동할 수 있습니다.</small></h3>
            <p><a class="btn btn-info" href="/user/userDetail" role="button">계정 관리</a></p>
        </div>

        <hr class="t">

        <div class="shadow-none jumbotron" id="manage-jumbotron">
            <div class="row">
                <div class="col-lg-4">
                    <p><i class="fas fa-users fa-4x"></i></p>
                    <br>
                    <h2>회원 관리</h2>
                    <p>회원 목록 조회, 회원 상세 조회, 회원가입 승인/비승인, 계정 삭제</p>
                    <p><a class="btn btn-info" href="/admin/userList" role="button">View details &raquo;</a></p>
                </div>

                <div class="col-lg-4">
                    <p><i class="fas fa-clipboard-list fa-4x"></i></p>
                    <br>
                    <h2>게시판 관리</h2>
                    <p>게시판 목록 조회, 게시물 상세 조회, 게시물 수정/삭제</p>
                    <p><a class="btn btn-info" href="#" role="button">View details &raquo;</a></p>
                </div>

                <div class="col-lg-4">
                    <p><i class="fas fa-shopping-cart fa-4x"></i></p>
                    <br>
                    <h2>대여 관리</h2>
                    <p>대여 품목 관리, 대여 목록 조회, 대여 상세 조회, 대여 상태 수정/삭제</p>
                    <p><a class="btn btn-info" href="#" role="button">View details &raquo;</a></p>
                </div>
            </div>
        </div>
    </div>

</body>