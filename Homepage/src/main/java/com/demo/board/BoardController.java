package com.demo.board;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.demo.global.utils.UserAuth;
import com.demo.model.BoardDTO;
import com.demo.model.CommentDTO;
import com.demo.model.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("boardController")
@RequestMapping(value = "/board/*")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardServiceImpl service;

    /*--------------------------------------------------- 게시판 홈 ---------------------------------------------------*/

    /**
     * GET Request: 게시판 인덱스 페이지
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @GetMapping(value = "/boardIndex")
    public void getBoardIndex() throws Exception {
        logger.info("getBoardIndex()");
    }

    /*-------------------------------------------------- 게시글 관리 --------------------------------------------------*/

    /**
     * GET Request: 게시글 목록 페이지 (1페이지)
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @GetMapping(value = "/boardList")
    public String getBoardList(Model model) throws Exception {
        logger.info("getBoardList()");

        List<BoardDTO> boardList = service.boardList("1");
        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }

    /**
     * GET Request: 게시글 페이징 목록 처리 (페이지 버튼(1, 2, ...) 클릭 시)
     */
//    @UserAuth(role = UserAuth.Roles.GUEST)
//    @GetMapping(value = "/boardLists")
//    public String getPagedBoardList(@RequestParam String pageNum,
//                                    @RequestParam String cycle,
//                                    Model model) throws Exception {
//        logger.info("getPagedBoardList()");
//
//        logger.info("CYCLE: " + cycle); // cycle 값은 서버로 전달되지 않음.
//        List<BoardDTO> boardList = service.boardList(pageNum);
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("cycle", cycle);
//
//        return "board/boardList";
//    }
    @UserAuth(role = UserAuth.Roles.GUEST)
    @GetMapping(value = "/boardLists")
    public String getPagedBoardList(@RequestParam String pageNum,
                                    Model model) throws Exception {
        logger.info("getPagedBoardList()");

        List<BoardDTO> boardList = service.boardList(pageNum);
        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }

    /**
     * GET Request: 게시글 상세 페이지
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @GetMapping(value="/boardDetail")
    public String getBoardDetail(@RequestParam String boardIdx, Model model) throws Exception {
        logger.info("getBoardDetail()");

        // 게시글 상세 조회
        BoardDTO board = service.boardDetail(boardIdx);
        service.updateHit(boardIdx);
        model.addAttribute("board", board);

        // 해당 게시글의 댓글 목록 조회
        List<CommentDTO> commentList = service.commentList(boardIdx);
        model.addAttribute("commentList", commentList);

        return "board/boardDetail";
    }

    /**
     * GET Request: 게시글 검색 처리
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @GetMapping(value = "/boardSearchedList")
    public String getBoardSearchedList(@RequestParam("searchOption") String searchOption,
                                       @RequestParam("searchKeyword") String searchKeyword,
                                       Model model) throws Exception {
        logger.info("getBoardSearchedList()");

        List<BoardDTO> boardSearchedList = service.searchBoard(searchOption, searchKeyword);
        model.addAttribute("boardList", boardSearchedList);

        return "board/boardList";
    }

    /**
     * GET Request: 게시글 작성 폼
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/createBoard")
    public void getCreateBoard(Model model) throws Exception {
        logger.info("getCreateBoard()");

        model.addAttribute("createBoard", new BoardDTO());
    }

    /**
     * POST Request: 게시글 작성 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @PostMapping(value = "/createBoard")
    public String postCreateBoard(@ModelAttribute("createBoard") @Valid BoardDTO board,
                                  BindingResult result, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
        logger.info("postCreateBoard()");

        if (result.hasErrors()) {
            // 유효성(제목/내용 글자수) 검증 실패
            return "board/createBoard";

        } else {
            // 게시글 작성 성공
            board.setBoardAuthor(request.getParameter("boardAuthor"));
            board.setBoardTitle(request.getParameter("boardTitle"));
            board.setBoardContent(request.getParameter("boardContent"));

            service.createBoard(board);

            rttr.addFlashAttribute("msg", "게시글이 등록되었습니다.");
            return "redirect:/board/boardList";
        }
    }

    /**
     * GET Request: 게시글 수정 폼
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value="/updateBoard")
    public String getUpdateBoard(@RequestParam String boardIdx, Model model) throws Exception {
        logger.info("getUpdateBoard()");

        BoardDTO board = service.boardDetail(boardIdx);
        model.addAttribute("updateBoard", board);

        return "board/updateBoard";
    }

    /**
     * POST Request: 게시글 수정 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @PostMapping(value = "/updateBoard")
    public String postUpdateBoard(@ModelAttribute("updateBoard") @Valid BoardDTO board,
                                  BindingResult result, RedirectAttributes rttr) throws Exception {
        logger.info("postUpdateBoard()");

        if (result.hasErrors()) {
            // 유효성(제목/내용 글자수) 검증 실패
            return "board/updateBoard";

        } else {
            // 게시글 수정 성공
            service.updateBoard(board);
            rttr.addFlashAttribute("msg", "게시글이 수정되었습니다.");
            return "redirect:/board/boardList";
        }
    }

    /**
     * GET Request: 게시글 삭제 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/deleteBoard")
    public String getDeleteBoard(@RequestParam String boardIdx) throws Exception {
        logger.info("getDeleteBoard()");

        service.deleteBoard(boardIdx);

        return "redirect:/board/boardList";
    }

    /**
     * GET Request: 좋아요 여부 체크 후, 분기
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/checkBoardLike")
    public String getCheckBoardLike(@RequestParam String boardIdx, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
        logger.info("getCheckBoardLike()");

        UserDTO user = (UserDTO) request.getSession().getAttribute("loggedUser");
        String userId = user.getUserId();

        int result = service.checkBoardLike(boardIdx, userId);

        if (result == 0) {
            service.createBoardLike(boardIdx, userId);
            rttr.addFlashAttribute("msg", "좋아요가 반영되었습니다.");
        } else if (result == 1) {
            service.deleteBoardLike(boardIdx, userId);
            rttr.addFlashAttribute("msg", "좋아요가 취소되었습니다.");
        }

        return "redirect:/board/boardDetail?boardIdx=" + boardIdx;
    }

    /*--------------------------------------------------- 댓글 관리 ---------------------------------------------------*/

    /**
     * POST Request: 댓글 작성 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @PostMapping(value = "/createComment")
    public String postCreateComment(HttpServletRequest request) throws Exception {
        logger.info("postCreateComment()");

        CommentDTO comment = new CommentDTO();

        String boardIdx = request.getParameter("boardIdx");
        comment.setCommentAuthor(request.getParameter("commentAuthor"));
        comment.setCommentContent(request.getParameter("commentContent"));

        service.createComment(boardIdx, comment);

        return "redirect:/board/boardList";
    }

    /**
     * GET Request: 댓글 수정 페이지
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/updateComment")
    public String getUpdateComment(@RequestParam String commentIdx, Model model) throws Exception {
        logger.info("getUpdateComment()");

        CommentDTO comment = service.commentDetail(commentIdx);
        model.addAttribute("comment", comment);

        return "board/updateComment";
    }

    /**
     * POST Request: 댓글 수정 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @PostMapping(value = "/updateComment")
    public String postUpdateComment(CommentDTO comment) throws Exception {
        logger.info("postUpdateComment()");

        service.updateComment(comment);

        return "redirect:/board/boardList";
    }

    /**
     * GET Request: 댓글 삭제 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/deleteComment")
    public String getDeleteComment(@RequestParam String commentIdx) throws Exception {
        logger.info("getDeleteComment()");

        service.deleteComment(commentIdx);

        return "redirect:/board/boardList";
    }
}