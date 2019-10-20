function onUpdateComment() {
    var commentForm = $('form[name="updateComment"]');

    if (commentForm.length === 0) {
        alert("댓글을 입력해야 합니다.");
        location.href = "/board/updateComment";
    }

    commentForm.method = "post";
    commentForm.submit();
    alert("댓글이 수정되었습니다.");
}