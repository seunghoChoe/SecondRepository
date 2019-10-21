package com.demo.repository;

import com.demo.model.Criteria;
import com.demo.model.Post;
import com.demo.model.PostLike;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 게시글 레포지토리 클래스
 */
@Repository
public class PostRepository {
    private static final Logger logger = LoggerFactory.getLogger(PostRepository.class);
    private static String namespace = "mappers.PostMapper";

    @Autowired
    private SqlSessionTemplate sql;

    // INSERT 작업                                                                                                      INSERT 작업

    /**
     * @INSERT: 게시글 등록
     */
    public int insertPost(Post post) {
        return sql.insert(namespace + ".insertPost", post);
    }

    /**
     * @INSERT: 게시글 좋아요 등록
     */
    public int insertPostLike(PostLike postLike) {
        return sql.insert(namespace + ".insertPostLike", postLike);
    }

    // SELECT 작업                                                                                                      SELECT 작업

    /**
     * @SELECT: 게시글 개수 조회
     */
    public int countAllPost(boolean postIsDeleted) {
        return sql.selectOne(namespace + ".countAllPost", postIsDeleted);
    }

    /**
     * @SELECT: 게시글 개수 조회 (검색)
     */
    public int countSearchPost(boolean postIsDeleted, String searchOption, String searchKeyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("postIsDeleted", postIsDeleted);
        map.put("searchOption", searchOption);
        map.put("searchKeyword", searchKeyword);
        return sql.selectOne(namespace + ".countSearchPost", map);
    }

    /**
     * @SELECT: 게시글 목록 조회
     */
    public List<Map<String, Object>> selectPostList(boolean postIsDeleted, Criteria criteria) {
        Map<String, Object> map = new HashMap<>();
        map.put("postIsDeleted", postIsDeleted);
        map.put("pageStart", criteria.getPageStart());
        map.put("perPageNum", criteria.getPerPageNumber());
        return sql.selectList(namespace + ".selectPostList", map);
    }

    /**
     * @SELECT: 게시글 목록 조회 (검색)
     */
    public List<Map<String, Object>> selectSearchPostList(boolean postIsDeleted, Criteria criteria, String searchOption, String searchKeyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("postIsDeleted", postIsDeleted);
        map.put("pageStart", criteria.getPageStart());
        map.put("perPageNum", criteria.getPerPageNumber());
        map.put("searchOption", searchOption);
        map.put("searchKeyword", searchKeyword);
        return sql.selectList(namespace + ".selectSearchPostList", map);
    }

    /**
     * @SELECT: 게시글 상세 조회
     */
    public Post selectPost(Post post) {
        return sql.selectOne(namespace + ".selectPost", post);
    }

    /**
     * @SELECT: 게시글 작성자 검사
     */
    public int countPostUserId(int postId, String loggedUserId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("loggedUserId", loggedUserId);
        return sql.selectOne(namespace + ".countPostUserId", map);
    }

    /**
     * @SELECT: 게시글 좋아요 검사
     */
    public int countPostLike(PostLike postLike) {
        return sql.selectOne(namespace + ".countPostLike", postLike);
    }

    // UPDATE 작업

    /**
     * @UPDATE: 게시글 수정
     */
    public int updatePost(Post post) {
        return sql.update(namespace + ".updatePost", post);
    }

    /**
     * @UPDATE: 게시글 삭제 수정
     */
    public int updatePostIsDeleted(Post post) {
        return sql.update(namespace + ".updatePostIsDeleted", post);
    }

    /**
     * @UPDATE: 게시글 조회수 수정
     */
    public int updatePostView(int postId) {
        return sql.update(namespace + ".updatePostView", postId);
    }

    /**
     * @UPDATE: 게시글 좋아요수 갱신
     */
    public int updatePostLike(boolean isAdd, int postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("isAdd", isAdd);
        map.put("postId", postId);
        return sql.update(namespace + ".updatePostLike", map);
    }

    /**
     * @UPDATE: 게시글 댓글수 갱신
     */
    public int updatePostComment(boolean isAdd, int postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("isAdd", isAdd);
        map.put("postId", postId);
        return sql.update(namespace + ".updatePostComment", map);
    }

    // DELETE 작업                                                                                                      DELETE 작업

    /**
     * @DELETE: 게시글 좋아요 삭제
     */
    public int deletePostLike(PostLike postLike) {
        return sql.delete(namespace + ".deletePostLike", postLike);
    }
}