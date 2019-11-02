package com.demo.model;

import java.util.Date;

public class CommentDTO {
    private int boardIdx;
    private int commentIdx;
    private Date commentCreateDate;
    private Date commentUpdateDate;
    private int commentLike;
    private String commentContent;
    private String commentAuthor;

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }

    public int getCommentIdx() {
        return commentIdx;
    }

    public void setCommentIdx(int commentIdx) {
        this.commentIdx = commentIdx;
    }

    public Date getCommentCreateDate() {
        return commentCreateDate;
    }

    public void setCommentCreateDate(Date commentCreateDate) {
        this.commentCreateDate = commentCreateDate;
    }

    public Date getCommentUpdateDate() {
        return commentUpdateDate;
    }

    public void setCommentUpdateDate(Date commentUpdateDate) {
        this.commentUpdateDate = commentUpdateDate;
    }

    public int getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(int commentLike) {
        this.commentLike = commentLike;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }
}
