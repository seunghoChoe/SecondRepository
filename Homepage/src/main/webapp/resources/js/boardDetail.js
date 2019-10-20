function onCreateComment() {
    var commentForm = $('form[name="createComment"]');

    if (commentForm.length === 0) {
        alert("댓글을 입력해야 합니다.");
        location.href = "/board/createComment";
    }

    commentForm.method = "post";
    commentForm.submit();
    alert("댓글이 등록되었습니다.");
}