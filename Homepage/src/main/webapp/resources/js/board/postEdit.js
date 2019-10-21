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

// 뒤로가기 (history.back() 만 사용할 경우, 문제가 있음)
function historyBack(postId) {
    // document.referrer&&-1!=document.referrer.indexOf("http://localhost:8080")?history.back():location.href="/board/post?postId=" + postId;

    if (document.referrer && document.referrer.indexOf("http://localhost:8080") !== -1) { // 히스토리가 있을 경우,
        history.back();
    }
    else { // 히스토리가 없을 경우,
        location.href = "/board/post?postId=" + postId;
    }
}

$(function () {
    var ckeditor_config = {
        resize_enabled: true,
        height: 500,
        enterMode: CKEDITOR.ENTER_BR,
        shiftEnterMode: CKEDITOR.ENTER_P
        // filebrowserUploadUrl: "/asd/asd/ckUpload"
    };

    CKEDITOR.replace('postContent', ckeditor_config);
});