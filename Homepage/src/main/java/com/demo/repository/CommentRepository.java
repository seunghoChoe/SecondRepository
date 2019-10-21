package com.demo.repository;

import com.demo.model.Comment;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 댓글 레포지토리 클래스
 */
@Repository
public class CommentRepository {
    private static final Logger logger = LoggerFactory.getLogger(CommentRepository.class);
    private static String namespace = "mappers.CommentMapper";

    @Autowired
    private SqlSessionTemplate sql;

    // INSERT 작업                                                                                                      INSERT 작업

    /**
     * @INSERT: 댓글 등록
     */
    public int insertComment(Comment comment) {
        return sql.insert(namespace + ".insertComment", comment);
    }

    // SELECT 작업                                                                                                      SELECT 작업

    /**
     * @SELECT: 댓글 개수 조회
     */
    public int countAllComment(int commentPostId) {
        return sql.selectOne(namespace + ".countAllComment", commentPostId);
    }

    /**
     * @SELECT: 댓글 목록 조회
     */
    public List<Comment> selectCommentList(int commentPostId) {
        return sql.selectList(namespace + ".selectCommentList", commentPostId);
    }

    /**
     * @SELECT: 댓글 상세 조회
     */
    public Comment selectComment(int commentId) {
        return sql.selectOne(namespace + ".selectComment", commentId);
    }

    /**
     * @SELECT: 댓글 작성자 검사
     */
    public int countCommentUserId(int commentId, String loggedUserId) {
        Map<String, Object> map = new HashMap<>();
        map.put("commentId", commentId);
        map.put("loggedUserId", loggedUserId);
        return sql.selectOne(namespace + ".countCommentUserId", map);
    }

    // UPDATE 작업                                                                                                      UPDATE 작업

    /**
     * @UPDATE: 댓글 수정
     */
    public int updateComment(Comment comment) {
        return sql.update(namespace + ".updateComment", comment);
    }

    // DELETE 작업                                                                                                      DELETE 작업

    /**
     * @DELETE: 댓글 삭제
     */
    public int deleteComment(Comment comment) {
        return sql.delete(namespace + ".deleteComment", comment);
    }
}