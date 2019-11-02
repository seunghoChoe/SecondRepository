package com.demo.board;

import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.demo.model.BoardDTO;
import com.demo.model.CommentDTO;

@Service("boardService")
public class BoardServiceImpl {

	@Inject
	private BoardDAOImpl boardDAO;

	// 게시글 페이징 목록
	public List<BoardDTO> boardList(String pageNum) throws Exception {
		int pgNum = Integer.parseInt(pageNum);
		pgNum = 10 * (pgNum - 1); // n번째 row 부터 10개씩

		return boardDAO.boardList(pgNum);
	}

	// 게시글 상세 내용
	public BoardDTO boardDetail(String boardIdx) throws Exception {
		return boardDAO.boardDetail(boardIdx);
	}

	// 게시글 검색
	public List<BoardDTO> searchBoard(String searchOption, String searchKeyword) throws Exception {
		switch (searchOption) {
			case "title":
				searchOption = "b_title";
				break;
			case "content":
				searchOption = "b_content";
				break;
			case "idx":
				searchOption = "b_idx";
				break;
			default:
				searchOption = "b_title";
				break;
		}

		return boardDAO.searchBoard(searchOption, searchKeyword);
	}

	// 게시글 작성
	public void createBoard(BoardDTO board) throws Exception {
		boardDAO.createBoard(board);
	}

	// 게시글 수정
	public void updateBoard(BoardDTO board) throws Exception {
		boardDAO.updateBoard(board);
	}

	// 게시글 삭제
	public void deleteBoard(String boardIdx) throws Exception {
		boardDAO.deleteBoard(boardIdx);
	}

	// 조회수 증가
	public void updateHit(String boardIdx) throws Exception {
		boardDAO.updateHit(boardIdx);
	}

	// 게시글 좋아요 여부 체크
	public int checkBoardLike(String boardIdx, String userId) throws Exception {
		return boardDAO.checkBoardLike(boardIdx, userId);
	}

	// 게시글 좋아요
	public void createBoardLike(String boardIdx, String userId) throws Exception {
		boardDAO.createBoardLike(boardIdx, userId);
	}

	// 게시글 좋아요 취소
	public void deleteBoardLike(String boardIdx, String userId) throws Exception {
		boardDAO.deleteBoardLike(boardIdx, userId);
	}

	// 댓글 목록
	public List<CommentDTO> commentList(String boardIdx) throws Exception {
		return boardDAO.commentList(boardIdx);
	}

	//댓글 상세 내용
	public CommentDTO commentDetail(String commentIdx) throws Exception {
		return boardDAO.commentDetail(commentIdx);
	}

	// 댓글 작성
	public void createComment(String boardIdx, CommentDTO comment) throws Exception {
		boardDAO.createComment(boardIdx, comment);
	}

	// 댓글 수정
	public void updateComment(CommentDTO comment) throws Exception {
		boardDAO.updateComment(comment);
	}

	// 댓글 삭제
	public void deleteComment(String commentIdx) throws Exception {
		boardDAO.deleteComment(commentIdx);
	}
}