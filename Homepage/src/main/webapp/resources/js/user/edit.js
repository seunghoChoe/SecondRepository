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
                $('#userNumberMessage').html("이미 사용 중인 학번입니다.").css('color', '#FF3636');
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
                $('#userEmailMessage').html("이미 사용 중인 이메일입니다.").css('color', '#FF3636');
            }
            $('#userEmailError').hide();
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}