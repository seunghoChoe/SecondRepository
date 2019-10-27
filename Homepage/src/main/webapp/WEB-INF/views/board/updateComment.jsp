<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <script type="text/javascript" src="/resources/js/updateComment.js?v=<%=System.currentTimeMillis() %>"></script>
    <title>명지대학교 융합소프트웨어학부 : 댓글 수정</title>
</head>

<body>
<div align="center" class="body" style="padding:10px">

    <form name="updateComment" method="post" action="/board/updateComment">
        <div>
            <!-- 댓글 영역 -->
            <table class="table table-bordered">
                <tr>
                    <th style="width: 60px">작성자</th>
                    <td style="width: 60px"><input type="hidden" value="${comment.commentAuthor}" name="commentAuthor" class="form-control"/>
                        ${comment.commentAuthor}
                    </td>
                    <th style="width: 60px">작성일:</th>
                    <td>
                        <fmt:formatDate value="${comment.commentCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <th style="width: 60px">수정일:</th>
                    <td>
                        <fmt:formatDate value="${comment.commentUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="6">
                        <textarea name="commentContent" id="commentContent" class="form-control" style="height: 100px">${comment.commentContent}</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input name="commentIdx" type="hidden" value="${comment.commentIdx}">
                        <input type="button" value="수정하기" onclick="onUpdateComment()" style="display: inline-block; width: 60px; height: 20px">
                        <input type="button" value="취소" onclick="window.location='/board/boardList'" style="width: 60px; height: 20px">
                    </td>
                </tr>
            </table>

        </div>
    </form>
</div>
</body>