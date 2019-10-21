package com.demo.controller;

import com.demo.model.*;
import com.demo.service.CommentServiceImpl;
import com.demo.service.PageMaker;
import com.demo.service.PostServiceImpl;
import com.demo.utility.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import static com.demo.utility.Constants.SERVER_MESSAGE;
import static com.demo.utility.UserSession.getUserSession;

/**
 * @Description: 게시판 컨트롤러 클래스 (GET/POST 메소드만 사용한다.)
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private CommentServiceImpl commentService;

//    /**
//     * @COMMON: 게시글(댓글) 작성자와 로그인 사용자 ID 비교 (DB 조회를 필요로 하지 않는다.)
//     */
//    public boolean userIdCheck(String userId, HttpServletRequest request) {
//        User userSession = getUserSession(request); // 로그인 사용자 세션
//        return userId.equals(userSession.getUserId()); // 게시글(댓글)의 작성자와 세션 ID 를 비교한다. (동일할 경우, True)
//    }

    /**
     * @COMMON: 게시글 작성자와 로그인 사용자 ID 비교 (동일할 경우, True)
     */
    private boolean postUserIdCheck(int postId, HttpServletRequest request) {
        return postService.countPostUserId(postId, getUserSession(request).getUserId());
    }

    /**
     * @COMMON: 댓글 작성자와 로그인 사용자 ID 비교 (동일할 경우, True)
     */
    private boolean commentUserIdCheck(int commentId, HttpServletRequest request) {
        return commentService.countCommentUserId(commentId, getUserSession(request).getUserId());
    }

    /**
     * @GET: 게시글 목록 조회 및 검색 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @GetMapping("/postList")
    public String postListGET(Criteria criteria, Model model,
                              @RequestParam(value = "searchOption", required = false) String searchOption,
                              @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
        logger.info("postListGET()");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);

        model.addAttribute("currentPage", criteria.getPage());
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("baseUrl", "/board/postList"); // 페이징 뷰의 재사용을 위해 URL 을 모델에 담는다.

        if (searchOption == null && searchKeyword == null) {
            pageMaker.setTotalCount(postService.countAllPost(false)); // 삭제되지 않은 게시글만
            model.addAttribute("postList", postService.selectPostList(false, criteria)); // 삭제되지 않은 게시글만

        } else {
            pageMaker.setTotalCount(postService.countSearchPost(false, searchOption, searchKeyword)); // 삭제되지 않은 게시글만
            model.addAttribute("postList", postService.selectSearchPostList(false, criteria, searchOption, searchKeyword)); // 삭제되지 않은 게시글만
            model.addAttribute("searchOption", searchOption); // 검색 옵션 유지
            model.addAttribute("searchKeyword", searchKeyword); // 검색 키워드 유지
        }

        return "board/postList";
    }

    /**
     * @GET: 게시글 상세 조회 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @GetMapping("/post")
    public String postGET(@RequestParam int postId, Model model, HttpServletRequest request) {
        logger.info("postGET()");

        model.addAttribute("post", postService.selectPost(postId, false));
        model.addAttribute("commentList", commentService.selectCommentList(postId)); // 댓글 목록 조회 (향후 댓글 목록 페이징 추가할 것)
        postService.updatePostView(postId); // 게시글 조회수 수정

        if (getUserSession(request) != null) {
            model.addAttribute("comment", new Comment()); // 로그인 사용자일 경우, 댓글 등록 폼 추가
        }

        return "board/post";
    }

    /**
     * @GET: 게시글 등록 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/newPost")
    public String newPostGET(Model model) {
        logger.info("newPostGET()");

        model.addAttribute("post", new Post());

        return "board/newPost";
    }

    /**
     * @POST: 게시글 등록 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/newPost")
    public String newPostPOST(@ModelAttribute("post") @Valid Post post, BindingResult result, HttpServletRequest request, RedirectAttributes rttr) {
        logger.info("newPostPOST()");

        if (result.hasErrors()) { // 유효성 검증 실패
            logger.info(result.toString());
            return "board/newPost";

        } else {
            post.setPostUserId(getUserSession(request).getUserId());
            postService.insertPost(post);
            rttr.addFlashAttribute(SERVER_MESSAGE, "게시글이 등록되었습니다.");
            return "redirect:/board/postList";
        }
    }

    /**
     * @GET: 게시글 수정 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/postEdit")
    public String postEditGET(@RequestParam int postId, Model model, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("postEditGET()");

        if (! postUserIdCheck(postId, request)) {
            // URI 접속 시, 수정/삭제가 가능하므로 작성자와 로그인 사용자를 비교해야 한다.
            rttr.addFlashAttribute(SERVER_MESSAGE, "게시글 작성자만 수정할 수 있습니다.");
            return "redirect:/board/post?postId=" + postId;
        }

        model.addAttribute("post", postService.selectPost(postId, false));

        return "board/postEdit";
    }

    /**
     * @POST: 게시글 수정 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/postEdit")
    public String postEditPOST(@ModelAttribute("post") @Valid Post post, BindingResult result, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("postEditPOST()");

        if (! postUserIdCheck(post.getPostId(), request)) {
            rttr.addFlashAttribute(SERVER_MESSAGE, "게시글 작성자만 수정할 수 있습니다.");
            return "redirect:/board/post?postId=" + post.getPostId();

        } else {
            if (result.hasErrors()) { // 유효성 검증 실패
                logger.info(result.toString());
                return "board/postEdit";

            } else {
                post.setPostUserId(getUserSession(request).getUserId()); // Hidden 값 변조에 대비한 DB 접근 후, ID 가져오기)
                postService.updatePost(post);
                rttr.addFlashAttribute(SERVER_MESSAGE, "게시글이 수정되었습니다.");
                return "redirect:/board/post?postId=" + post.getPostId();
            }
        }
    }

    /**
     * @POST: 게시글 삭제 수정 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/postDelete")
    public @ResponseBody int postDeleteGET(@RequestParam int postId, HttpServletRequest request) {
        logger.info("postDeleteGET()");

        if (! postUserIdCheck(postId, request)) { // 작성자/로그인 사용자 불일치
            return 0;
        } else {
            postService.updatePostIsDeleted(postId, getUserSession(request).getUserId(), true);
            return 1;
        }
    }

    /**
     * @POST: 게시글 좋아요 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/postLike")
    public @ResponseBody void postLikeGET(@RequestParam int postId, HttpServletRequest request) {
        logger.info("postLikeGET()");

        postService.updatePostLike(postId, getUserSession(request).getUserId());
    }

    /**
     * @POST: 댓글 등록 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/newComment")
    public String newCommentPOST(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, HttpServletRequest request, RedirectAttributes rttr) {
        logger.info("newCommentPOST()");

        if (result.hasErrors()) {
            logger.info(result.toString());
            rttr.addFlashAttribute(SERVER_MESSAGE, "입력 정보를 확인하세요.");
            return "redirect:/board/post?postId=" + comment.getCommentPostId(); // 유효성 검증 실패

        } else {
            comment.setCommentUserId(getUserSession(request).getUserId()); // 사용자 ID 추가
            commentService.insertComment(comment);
            postService.updatePostComment(comment.getCommentPostId(), true); // 댓글수 수정(+)
            rttr.addFlashAttribute(SERVER_MESSAGE, "댓글이 등록되었습니다.");
            return "redirect:/board/post?postId=" + comment.getCommentPostId();
        }
    }


    /**
     * @GET: 댓글 수정 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/commentEdit")
    public String commentEditGET(@RequestParam int commentPostId, @RequestParam int commentId, Model model, HttpServletRequest request, RedirectAttributes rttr) {
        logger.info("commentEditGET()");

        if (! commentUserIdCheck(commentId, request)) {
            rttr.addFlashAttribute(SERVER_MESSAGE, "댓글 작성자만 삭제할 수 있습니다.");
            return "redirect:/board/post?postId=" + commentPostId;
        }

        model.addAttribute("comment", commentService.selectComment(commentId));

        return "board/commentEdit";
    }

    /**
     * @POST: 댓글 수정 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/commentEdit")
    public String commentEditPOST(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, HttpServletRequest request, RedirectAttributes rttr) {
        logger.info("commentEditPOST()");

        if (! commentUserIdCheck(comment.getCommentId(), request)) {
            rttr.addFlashAttribute(SERVER_MESSAGE, "댓글 작성자만 수정할 수 있습니다.");
            return "redirect:/board/post?postId=" + comment.getCommentPostId();

        } else {
            if (result.hasErrors()) {
                logger.info(result.toString());
                rttr.addFlashAttribute(SERVER_MESSAGE, "입력 정보를 확인하세요.");
                return "redirect:/board/commentEdit?commentPostId=" + comment.getCommentPostId() + "&commentId=" + comment.getCommentId(); // 유효성 검증 실패

            } else {
                comment.setCommentUserId(getUserSession(request).getUserId()); // Hidden 값 변조에 대비한 DB 접근 후, ID 가져오기)
                commentService.updateComment(comment);
                rttr.addFlashAttribute(SERVER_MESSAGE, "댓글이 수정되었습니다.");
                return "redirect:/board/post?postId=" + comment.getCommentPostId();
            }
        }
    }

    /**
     * @POST: 댓글 삭제 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/commentDelete")
    public @ResponseBody int commentDeletePOST(@RequestParam int commentPostId, @RequestParam int commentId, HttpServletRequest request) {
        logger.info("commentDeletePOST()");

        if (! commentUserIdCheck(commentId, request)) { // 작성자/로그인 사용자 불일치
            return 0;
        } else {
            commentService.deleteComment(commentId, getUserSession(request).getUserId());
            postService.updatePostComment(commentPostId, false); // 댓글수 수정(-)
            return 1;
        }
    }
}