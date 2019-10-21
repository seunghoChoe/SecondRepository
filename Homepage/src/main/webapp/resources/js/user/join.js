// ID 체크
function checkUserId() {
    var userId = $('#userId').val();
    var regexp = /^[a-z0-9][a-z0-9_\-]{4,16}$/;

    $.ajax({
        type: 'POST',
        url: '/user/idCheck',
        data: {userId: userId},

        success: function (result) {
            if (regexp.test(userId) && result === 0) {
                $('#userIdMessage').html("사용 가능한 ID 입니다.").css('color', '#0098F7');
            } else if (! regexp.test(userId)) {
                $('#userIdMessage').html("5~15자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.").css('color', '#FF3636');
            } else {
                $('#userIdMessage').html("사용할 수 없는 ID 입니다.").css('color', '#FF3636');
            }
            $('#userIdError').hide();
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}

// 비밀번호 및 비밀번호 확인 체크
function checkUserPassword() {
    var userPassword = $('#userPassword').val();
    var confirmPassword = $('#confirmPassword').val();
    var regexp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;

    if (regexp.test(userPassword) && userPassword === confirmPassword) {
        $('#userPasswordMessage').html("비밀번호가 일치합니다.").css('color', '#0098F7');
    } else if (! regexp.test(userPassword)) {
        $('#userPasswordMessage').html("8~15자의 영문 대 소문자, 숫자, 특수문자를 최소 1개씩 사용하세요.").css('color', '#FF3636');
    } else {
        $('#userPasswordMessage').html("비밀번호가 일치하지 않습니다.").css('color', '#FF3636');
    }
    $('#userPasswordError').hide();
}

// 이름 체크
function checkUserName() {
    var userName = $('#userName').val();
    var regexp = /^[가-힣]{2,5}$/;

    if (regexp.test(userName)) {
        $('#userNameMessage').html("사용 가능한 이름입니다.").css('color', '#0098F7');
    } else {
        $('#userNameMessage').html("2~5자의 한글만 사용 가능합니다.").css('color', '#FF3636');
    }
    $('#userNameError').hide();
}

// 학번 체크
function checkUserNumber() {
    var userNumber = $('#userNumber').val();
    var regexp = /^[0-9]{8}$/;

    $.ajax({
        type: 'POST',
        url: '/user/numberCheck',
        data: {userNumber: userNumber},

        success: function (result) {
            if (regexp.test(userNumber) && result === 0) {
                $('#userNumberMessage').html("사용 가능한 학번입니다.").css('color', '#0098F7');
            } else if (! regexp.test(userNumber)) {
                $('#userNumberMessage').html("8자의 숫자만 사용 가능합니다.").css('color', '#FF3636');
            } else {
                $('#userNumberMessage').html("사용할 수 없는 학번입니다.").css('color', '#FF3636');
            }
            $('#userNumberError').hide();
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}

// 이메일 체크
function checkUserEmail() {
    var userEmail = $('#userEmail').val();
    var regexp = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

    $.ajax({
        type: 'POST',
        url: '/user/emailCheck',
        data: {userEmail: userEmail},

        success: function (result) {
            if (regexp.test(userEmail) && result === 0) {
                $('#userEmailMessage').html("사용 가능한 이메일입니다.").css('color', '#0098F7');
            } else if (! regexp.test(userEmail)) {
                $('#userEmailMessage').html("이메일 형식이 맞지 않습니다.").css('color', '#FF3636');
            } else {
                $('#userEmailMessage').html("사용할 수 없는 이메일입니다.").css('color', '#FF3636');
            }
            $('#userEmailError').hide();
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}