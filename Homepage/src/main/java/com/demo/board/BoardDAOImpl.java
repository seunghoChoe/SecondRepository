package com.demo.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.BoardDTO;
import com.demo.model.CommentDTO;

//@Transactional
@Repository("boardDAO")
public class BoardDAOImpl {

	private static String namespace = "mappers.BoardMapper";

	@Inject
	private SqlSession sql;

	// 게시글 페이징 목록
	public List<BoardDTO> boardList(int pageNum) throws Exception {
		return sql.selectList(namespace + ".boardList" , pageNum);
	}

	// 게시글 상세 내용
	public BoardDTO boardDetail(String boardIdx) throws Exception {
		return sql.selectOne(namespace + ".boardDetail", boardIdx);
	}

	// 게시글 검색
	public List<BoardDTO> searchBoard(String searchOption, String searchKeyword) throws Exception {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("searchOption", searchOption);
		paramsMap.put("searchKeyword", searchKeyword);

		// 게시글 번호 검색 시 정확한 인덱스 값을 가진 게시글만 출력되도록 함 (예를 들어 6 을 검색하면 61, 62 등이 포함되는 문제)
		if (searchOption.equals("b_idx")) {
			return sql.selectList(namespace + ".searchBoardIdx", paramsMap);
		} else {
			return sql.selectList(namespace + ".searchBoard", paramsMap);
		}
	}

	// 게시글 작성
	public int createBoard(BoardDTO board) throws Exception {
		return sql.insert(namespace + ".createBoard", board);
	}

	// 게시글 수정
	public int updateBoard(BoardDTO board) throws Exception {
		return sql.update(namespace + ".updateBoard", board);
	}

	// 게시글 삭제
	public int deleteBoard(String boardIdx) throws Exception {
		return sql.delete(namespace + ".deleteBoard", boardIdx);
	}

	// 조회수 증가
	public int updateHit(String boardIdx) throws Exception {
		return sql.update(namespace + ".updateHit", boardIdx);
	}

	// 게시글 좋아요 여부 체크
	public int checkBoardLike(String boardIdx, String userId) throws Exception {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("boardIdx", boardIdx);
		paramsMap.put("userId", userId);
		return sql.selectOne(namespace + ".checkBoardLike", paramsMap);
	}

	// 게시글 좋아요
	public int createBoardLike(String boardIdx, String userId) throws Exception {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("boardIdx", boardIdx);
		paramsMap.put("userId", userId);
		sql.update(namespace + ".createBoardLike", paramsMap);
		return sql.update(namespace + ".increaseBoardLike", paramsMap);
	}

	// 게시글 좋아요 취소
	public int deleteBoardLike(String boardIdx, String userId) throws Exception {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("boardIdx", boardIdx);
		paramsMap.put("userId", userId);
		sql.update(namespace + ".deleteBoardLike", paramsMap);
		return sql.update(namespace + ".decreaseBoardLike", paramsMap);
	}

	// 댓글 목록
	public List<CommentDTO> commentList(String boardIdx) throws Exception {
		return sql.selectList(namespace + ".commentList", boardIdx);
	}

	// 댓글 상세 내용
	public CommentDTO commentDetail(String commentIdx) throws Exception {
		return sql.selectOne(namespace + ".commentDetail", commentIdx);
	}

	// 댓글 작성
	public int createComment(String boardIdx, CommentDTO comment) throws Exception {
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("boardIdx", boardIdx);
		paramsMap.put("commentAuthor", comment.getCommentAuthor());
		paramsMap.put("commentContent", comment.getCommentContent());
		return sql.insert(namespace + ".createComment", paramsMap);
	}

	// 댓글 수정
	public int updateComment(CommentDTO comment) throws Exception {
		return sql.update(namespace + ".updateComment", comment);
	}

	// 댓글 삭제
	public int deleteComment(String commentIdx) throws Exception {
		return sql.delete(namespace + ".deleteComment", commentIdx);
	}
}