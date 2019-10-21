function checkContentLength() {
    $('#contentCounter').html("(" + document.getElementById("commentContent").value.length + " / 100자)");

    if (document.getElementById("commentContent").value.length >= 100) {
        alert("댓글은 1~100자 이내로 작성할 수 있습니다.");
    }
}

// 내용 에러 메시지 감추기
function hideErrorContent() {
    $('#errorContent').hide();
}