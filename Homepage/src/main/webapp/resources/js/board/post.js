// 게시글 수정 폼
function postEdit(postId) {
    location.href = '/board/postEdit?postId=' + postId;
}

// 게시글 삭제
function postDelete(postId) {
    var message = confirm("게시글을 삭제하시겠습니까?");
    if (message) {
        $.ajax({
            type: 'POST',
            url: '/board/postDelete',
            data: {postId: postId},

            success: function (result) {
                console.log(result);
                if (result === 0) {
                    alert("게시글 작성자만 삭제할 수 있습니다.");
                } else {
                    alert("게시글이 삭제되었습니다.");
                    location.href = '/board/postList';
                }
            },
            error: function () {
                alert("오류가 발생하였습니다.");
            }
        });
    }
}

// 게시글 좋아요
function postLike(postId) {
    $.ajax({
        type: 'POST',
        url: '/board/postLike',
        data: {postId: postId},

        success: function () {
            alert("♥");
            location.href = '/board/post?postId=' + postId;
        },
        error: function () {
            alert("오류가 발생하였습니다.");
        }
    });
}

// 댓글 수정 폼
function commentEdit(commentPostId, commentId) {
    location.href = '/board/commentEdit?commentPostId=' + commentPostId + '&commentId=' + commentId;
}

// 댓글 삭제
function commentDelete(commentPostId, commentId) {
    var message = confirm("댓글을 삭제하시겠습니까?");
    if (message) {
        $.ajax({
            type: 'POST',
            url: '/board/commentDelete',
            data: {commentPostId: commentPostId, commentId: commentId},

            success: function (result) {
                if (result === 0) {
                    alert("댓글 작성자만 삭제할 수 있습니다.");
                } else {
                    alert("댓글이 삭제되었습니다.");
                    location.href = '/board/post?postId=' + commentPostId;
                }
            },
            error: function () {
                alert("오류가 발생하였습니다.");
            }
        });
    }
}

// 댓글 좋아요
function commentLike(commentId) {
    // ...
}