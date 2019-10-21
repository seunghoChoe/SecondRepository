package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 댓글 모델 클래스
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int commentId; // 댓글 번호
    private int commentPostId; // 게시글 번호
    private String commentUserId; // 댓글 작성자

	@Size(min = 1, max = 100, message = "댓글은 1~100자 이내로 작성해야 합니다.")
    private String commentContent; // 댓글 내용

    private Date commentCreatedAt; // 댓글 생성 일자
    private Date commentUpdatedAt; // 댓글 수정 일자
    private int commentLike; // 댓글 좋아요

    public Comment() { }

	public Comment(int commentId, int commentPostId, String commentUserId,
				   String commentContent, Date commentCreatedAt, Date commentUpdatedAt, int commentLike) {
        this.commentId = commentId;
        this.commentPostId = commentPostId;
        this.commentUserId = commentUserId;
        this.commentContent = commentContent;
        this.commentCreatedAt = commentCreatedAt;
        this.commentUpdatedAt = commentUpdatedAt;
        this.commentLike = commentLike;
    }

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getCommentPostId() {
		return commentPostId;
	}

	public void setCommentPostId(int commentPostId) {
		this.commentPostId = commentPostId;
	}

	public String getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentCreatedAt() {
		return commentCreatedAt;
	}

	public void setCommentCreatedAt(Date commentCreatedAt) {
		this.commentCreatedAt = commentCreatedAt;
	}

	public Date getCommentUpdatedAt() {
		return commentUpdatedAt;
	}

	public void setCommentUpdatedAt(Date commentUpdatedAt) {
		this.commentUpdatedAt = commentUpdatedAt;
	}

	public int getCommentLike() {
		return commentLike;
	}

	public void setCommentLike(int commentLike) {
		this.commentLike = commentLike;
	}
}