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