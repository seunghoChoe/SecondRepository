function checkTitleLength() {
    $('#titleCounter').html("(" + document.getElementById("postTitle").value.length + " / 100자)");

    if (document.getElementById("postTitle").value.length >= 100) {
        alert("게시글 제목은 1~100자 이내로 작성할 수 있습니다.");
    }
}

// 제목 에러 메시지 감추기
function hideErrorTitle() {
    $('#errorPostTitle').hide();
}

// 내용 에러 메시지 감추기
function hideErrorContent() {
    $('#errorPostContent').hide();
}

$(function () {
    var ckeditor_config = {
        resize_enabled: true,
        height: 500,
        enterMode: CKEDITOR.ENTER_BR,
        shiftEnterMode: CKEDITOR.ENTER_P,
        filebrowserUploadUrl: "/imageUpload"
    };

    CKEDITOR.replace('postContent', ckeditor_config);
});