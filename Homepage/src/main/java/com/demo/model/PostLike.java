package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * @Description: 게시글 좋아요 모델 클래스
 */
public class PostLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int likePostId; // 좋아요 게시글 번호
    private String likeUserId; // 좋아요 계정 ID

    public PostLike() { }

    public PostLike(int likePostId, String likeUserId) {
        this.likePostId = likePostId;
        this.likeUserId = likeUserId;
    }

    public int getLikePostId() {
        return likePostId;
    }

    public void setLikePostId(int likePostId) {
        this.likePostId = likePostId;
    }

    public String getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(String likeUserId) {
        this.likeUserId = likeUserId;
    }
}