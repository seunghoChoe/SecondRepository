package com.demo.service;

import com.demo.model.Criteria;
import com.demo.model.Post;
import com.demo.model.PostLike;
import com.demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Description: 게시글 서비스 클래스 (개발 완료 시, 인터페이스 클래스를 생성하도록 한다.)
 */
@Service
public class PostServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    /**
     * @INSERT: 게시글 등록
     */
    public int insertPost(Post post) {
        return postRepository.insertPost(post);
    }

    /**
     * @SELECT: 게시글 개수 조회
     */
    public int countAllPost(boolean postIsDeleted) {
        return postRepository.countAllPost(postIsDeleted);
    }

    /**
     * @SELECT: 게시글 개수 조회 (검색)
     */
    public int countSearchPost(boolean postIsDeleted, String searchOption, String searchKeyword) {
        return postRepository.countSearchPost(postIsDeleted, searchOption, searchKeyword);
    }

    /**
     * @SELECT: 게시글 목록 조회
     */
    public List<Map<String, Object>> selectPostList(boolean postIsDeleted, Criteria criteria) {
        return postRepository.selectPostList(postIsDeleted, criteria);
    }

    /**
     * @SELECT: 게시글 목록 조회 (검색)
     */
    public List<Map<String, Object>> selectSearchPostList(boolean postIsDeleted, Criteria criteria, String searchOption, String searchKeyword) {
        return postRepository.selectSearchPostList(postIsDeleted, criteria, searchOption, searchKeyword);
    }

    /**
     * @SELECT: 게시글 상세 조회
     */
    public Post selectPost(int postId, boolean postIsDeleted) {
        Post post = new Post();
        post.setPostId(postId);
        post.setPostIsDeleted(postIsDeleted);
        return postRepository.selectPost(post);
    }

    /**
     * @SELECT: 게시글 작성자 검사
     */
    public boolean countPostUserId(int postId, String loggedUserId) {
        // 동일할 경우, DB 에서 1을 반환한다. 이 값을 1과 비교하여 boolean 값을 반환한다.
        return postRepository.countPostUserId(postId, loggedUserId) == 1;
    }

    /**
     * @SELECT: 게시글 좋아요 검사
     */
    public int countPostLike(PostLike postLike) {
        return postRepository.countPostLike(postLike);
    }

    /**
     * @UPDATE: 게시글 수정
     */
    public int updatePost(Post post) {
        return postRepository.updatePost(post);
    }

    /**
     * @UPDATE: 게시글 삭제 수정
     */
    public int updatePostIsDeleted(int postId, String loggedUserId, boolean postIsDeleted) {
        Post post = new Post(); // 향후 생성자 추가로 단순화하기
        post.setPostId(postId);
        post.setPostUserId(loggedUserId);
        post.setPostIsDeleted(postIsDeleted);
        return postRepository.updatePostIsDeleted(post);
    }

    /**
     * @UPDATE: 게시글 조회수 수정
     */
    public int updatePostView(int postId) {
        return postRepository.updatePostView(postId);
    }

    /**
     * @UPDATE: 게시글 댓글수 수정
     */
    public int updatePostComment(int postId, boolean isAdd) {
        return postRepository.updatePostComment(isAdd, postId);
    }

    /**
     * @CRUD: 게시글 좋아요
     */
    public int updatePostLike(int postId, String userId) {
        PostLike postLike = new PostLike(); // 향후 생성자 추가로 단순화하기
        postLike.setLikePostId(postId);
        postLike.setLikeUserId(userId);

        int result = this.countPostLike(postLike); // 게시글 좋아요 검사

        if (result == 0) { // 게시글을 좋아요 하지 않은 상태
            this.postRepository.insertPostLike(postLike); // 게시글 좋아요
            this.postRepository.updatePostLike(true, postId); // 게시글 좋아요수 수정(+)
        } else if (result == 1) { // 게시글을 좋아요 한 상태
            this.postRepository.deletePostLike(postLike); // 게시글 좋아요 취소
            this.postRepository.updatePostLike(false, postId); // 게시글 좋아요수 수정(-)
        }
        return result;
    }
}