function checkTitleLength() {
    $('#titleCounter').html("(" + document.getElementById("boardTitle").value.length + " / 100자)");
    if (document.getElementById("boardTitle").value.length >= 100)
        alert("게시글 제목은 한글/영문 100자로 제한됩니다.");
}

// function checkContentLength() {
//     $('#contentCounter').html("(" + document.getElementById("boardContent").value.length + " / 1000자)");
//     if (document.getElementById("boardContent").value.length >= 1000)
//         alert("게시글 내용은 한글/영문 1000자로 제한됩니다.");
//} --> 에디터 사용 시 ID 속성 찾기 불가능

// 제목 에러 메시지 감추기
function checkTitle() {
    $('#errorTitle').hide();
}

// 내용 에러 메시지 감추기
function checkContent() {
    $('#errorContent').hide();
}

$(function () {
    var ckeditor_config = {
        resize_enabled: true,
        height: 500,
        enterMode: CKEDITOR.ENTER_BR,
        shiftEnterMode: CKEDITOR.ENTER_P,
        // filebrowserUploadUrl: "/asd/asd/ckUpload"
    };

    CKEDITOR.replace('boardContent', ckeditor_config);
});