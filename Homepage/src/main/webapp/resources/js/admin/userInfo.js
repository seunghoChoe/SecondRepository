function userActiveEdit(userId, userIsEnabled) {
    var message = "";

    if (userIsEnabled === true) {
        message = confirm("계정을 활성화 하시겠습니까?");
    } else {
        message = confirm("계정을 비활성화 하시겠습니까?");
    }

    if (message) {
        $.ajax({
            type: 'POST',
            url: '/admin/userActiveEdit',
            data: {userId: userId, userIsEnabled: userIsEnabled},

            success: function (result) {
                if (result) {
                    alert("계정이 활성화 되었습니다.");
                } else {
                    alert("계정이 비활성화 되었습니다.");
                }
                location.href = '/admin/userList';
            },
            error: function () {
                alert("오류가 발생하였습니다.");
            }
        });
    }
}

function userDeleteEdit(userId, userIsDeleted) {
    var message = "";

    if (userIsDeleted === true) {
        message = confirm("계정을 탈퇴 처리하시겠습니까?");
    } else {
        message = confirm("계정을 복구하시겠습니까?");
    }

    if (message) {
        $.ajax({
            type: 'POST',
            url: '/admin/userDeleteEdit',
            data: {userId: userId, userIsDeleted: userIsDeleted},

            success: function (result) {
                if (result) {
                    alert("계정이 탈퇴 처리되었습니다.");
                } else {
                    alert("계정이 복구되었습니다.");
                }
                location.href = '/admin/userList';
            },
            error: function () {
                alert("오류가 발생하였습니다.");
            }
        });
    }
}