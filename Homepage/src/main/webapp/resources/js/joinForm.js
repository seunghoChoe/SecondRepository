// ID 체크
function checkId() {
    var inputId = $('#userId').val();
    var regexp  = /^[a-z0-9][a-z0-9_\-]{4,16}$/;

    $.ajax({
        type: 'POST',
        url: '/user/checkId',
        data: {userId: inputId},

        success: function (checkResult) {
            if (regexp.test(inputId) && checkResult === 0) {
                $('#showIdMsg').html("사용 가능한 ID 입니다.");
            } else if (! regexp.test(inputId)) {
                $('#showIdMsg').html("5~15자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
            } else {
                $('#showIdMsg').html("사용할 수 없는 ID 입니다.");
            }
            $('#errorId').hide();
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}

// 비밀번호 및 비밀번호 확인 체크
function checkPw() {
    var inputPw   = $('#userPw').val();
    var confirmPw = $('#confirmPw').val();
    var regexp    = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;

    if (regexp.test(inputPw) && inputPw === confirmPw) {
        $('#showPwMsg').html("비밀번호가 일치합니다.");
    } else if (! regexp.test(inputPw)) {
        $('#showPwMsg').html("8~15자의 영문 대 소문자, 숫자, 특수문자를 최소 1개씩 사용하세요.");
    } else {
        $('#showPwMsg').html("비밀번호가 일치하지 않습니다.");
    }
    $('#errorPw').hide();
}

// 이름 체크
function checkName() {
    var inputName = $('#userName').val();
    var regexp    = /^[가-힣]{2,5}$/;

    if (regexp.test(inputName)) {
        $('#showNameMsg').html("사용 가능한 이름입니다.");
    } else {
        $('#showNameMsg').html("2~5자의 한글만 사용 가능합니다.");
    }
    $('#errorName').hide();
}

// 학번 체크
function checkSno() {
    var inputSno = $('#userSno').val();
    var regexp   = /^[0-9]{8}$/;

    $.ajax({
        type: 'POST',
        url: '/user/checkSno',
        data: {userSno: inputSno},

        success: function (checkResult) {
            if (regexp.test(inputSno) && checkResult === 0) {
                $('#showSnoMsg').html("사용 가능한 학번입니다.");
            } else if (! regexp.test(inputSno)) {
                $('#showSnoMsg').html("8자의 숫자만 사용 가능합니다.");
            } else {
                $('#showSnoMsg').html("사용할 수 없는 학번입니다.");
            }
            $('#errorSno').hide();
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}

// 이메일 체크
function checkEmail() {
    var inputEmail = $('#userEmail').val();
    var regexp     = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

    $.ajax({
        type: 'POST',
        url: '/user/checkEmail',
        data: {userEmail: inputEmail},

        success: function (checkResult) {
            if (regexp.test(inputEmail) && checkResult === 0) {
                $('#showEmailMsg').html("사용 가능한 이메일입니다.");
            } else if (! regexp.test(inputEmail)) {
                $('#showEmailMsg').html("이메일 형식이 맞지 않습니다.");
            } else {
                $('#showEmailMsg').html("사용할 수 없는 이메일입니다.");
            }
            $('#errorEmail').hide();
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}