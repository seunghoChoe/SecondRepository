package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 게시글 모델 클래스
 */
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    private int postId; // 게시글 번호
    private String postUserId; // 게시글 작성자

    @Size(min = 1, max = 100, message = "게시글 제목은 1~100자 이내로 작성해야 합니다.")
    private String postTitle; // 게시글 제목
    @Size(min = 1, max = 2500, message = "게시글 내용은 1~2500자 이내로 작성해야 합니다.")
    private String postContent; // 게시글 내용

    private Date postCreatedAt; // 게시글 작성일
    private Date postUpdatedAt; // 게시글 수정일
    private int postView; // 게시글 조회수
    private int postLike; // 게시글 좋아요
	private int postComment; // 게시글 댓글수
    private boolean postIsDeleted; // 게시글 삭제 여부(0: 삭제X, 1: 삭제O)

    public Post() { }

	public Post(int postId, String postUserId,
				String postTitle, String postContent,
				Date postCreatedAt, Date postUpdatedAt,
				int postView, int postLike, int postComment,
				boolean postIsDeleted) {
		this.postId = postId;
		this.postUserId = postUserId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postCreatedAt = postCreatedAt;
		this.postUpdatedAt = postUpdatedAt;
		this.postView = postView;
		this.postLike = postLike;
		this.postComment = postComment;
		this.postIsDeleted = postIsDeleted;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostUserId() {
		return postUserId;
	}

	public void setPostUserId(String postUserId) {
		this.postUserId = postUserId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getPostCreatedAt() {
		return postCreatedAt;
	}

	public void setPostCreatedAt(Date postCreatedAt) {
		this.postCreatedAt = postCreatedAt;
	}

	public Date getPostUpdatedAt() {
		return postUpdatedAt;
	}

	public void setPostUpdatedAt(Date postUpdatedAt) {
		this.postUpdatedAt = postUpdatedAt;
	}

	public int getPostView() {
		return postView;
	}

	public void setPostView(int postView) {
		this.postView = postView;
	}

	public int getPostLike() {
		return postLike;
	}

	public void setPostLike(int postLike) {
		this.postLike = postLike;
	}

	public int getPostComment() {
		return postComment;
	}

	public void setPostComment(int postComment) {
		this.postComment = postComment;
	}

	public boolean getPostIsDeleted() {
		return postIsDeleted;
	}

	public void setPostIsDeleted(boolean postIsDeleted) {
		this.postIsDeleted = postIsDeleted;
	}
}