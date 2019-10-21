package com.demo.service;

import com.demo.model.Comment;
import com.demo.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 댓글 서비스 클래스 (개발 완료 시, 인터페이스 클래스를 생성하도록 한다.)
 */
@Service
public class CommentServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    /**
     * @INSERT: 댓글 등록
     */
    public int insertComment(Comment comment) {
        return commentRepository.insertComment(comment);
    }

    /**
     * @SELECT: 댓글 개수 조회
     */
    public int countAllComment(int commentPostId) {
        return commentRepository.countAllComment(commentPostId);
    }

    /**
     * @SELECT: 댓글 목록 조회
     */
    public List<Comment> selectCommentList(int commentPostId) {
        return commentRepository.selectCommentList(commentPostId);
    }

    /**
     * @SELECT: 댓글 상세 조회
     */
    public Comment selectComment(int commentId) {
        return commentRepository.selectComment(commentId);
    }

    /**
     * @SELECT: 댓글 작성자 검사
     */
    public boolean countCommentUserId(int commentId, String loggedUserId) {
        // 동일할 경우, DB 에서 1을 반환한다. 이 값을 1과 비교하여 boolean 값을 반환한다.
        return commentRepository.countCommentUserId(commentId, loggedUserId) == 1;
    }

    /**
     * @UPDATE: 댓글 수정
     */
    public int updateComment(Comment comment) {
        return commentRepository.updateComment(comment);
    }

    /**
     * @UPDATE: 댓글 삭제
     */
    public int deleteComment(int commentId, String loggedUserId) {
        Comment comment = new Comment(); // 향후 생성자 추가로 단순화하기
        comment.setCommentId(commentId);
        comment.setCommentUserId(loggedUserId);
        return commentRepository.deleteComment(comment);
    }
}