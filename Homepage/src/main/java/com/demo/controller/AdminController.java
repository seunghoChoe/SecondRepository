package com.demo.controller;

import com.demo.model.Criteria;
import com.demo.model.User;
import com.demo.service.CommentServiceImpl;
import com.demo.service.PageMaker;
import com.demo.service.PostServiceImpl;
import com.demo.service.UserServiceImpl;
import com.demo.utility.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @Description: 관리자 컨트롤러 클래스 (GET/POST 메소드만 사용한다.)
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private CommentServiceImpl commentService;

    /**
     * @GET: 관리자 메인 페이지
     */
    @UserRole(type = UserRole.Type.ADMIN)
    @GetMapping("/home")
    public String homeGET() {
        logger.info("homeGET()");

        return "/admin/home";
    }

    /**
     * @GET: 계정 목록 조회
     */
    @UserRole(type = UserRole.Type.ADMIN)
    @GetMapping("/userList")
    public String userListGET(Criteria criteria, Model model) {
        logger.info("userListGET()");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(criteria);
        pageMaker.setTotalCount(userService.countAllUser());

        List<Map<String, Object>> userList = userService.selectUserList(criteria);
        model.addAttribute("currentPage", criteria.getPage());
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("baseUrl", "/admin/userList"); // 페이징 뷰의 재사용을 위해 URL 을 모델에 담는다.
        model.addAttribute("userList", userList);

        return "admin/userList";
    }

    /**
     * @GET: 계정 상세 조회
     */
    @UserRole(type = UserRole.Type.ADMIN)
    @GetMapping("/userInfo")
    public String userInfoGET(@RequestParam String userId, Model model) {
        logger.info("userInfoGET()");

        model.addAttribute("user", userService.selectUser(new User(userId), false));

        return "admin/userInfo";
    }

    /**
     * @POST 계정 활성 수정
     */
    @UserRole(type = UserRole.Type.ADMIN)
    @PostMapping("/userActiveEdit")
    public @ResponseBody boolean userActiveEditPOST(@RequestParam String userId, @RequestParam boolean userIsEnabled) {
        logger.info("userActiveEditPOST()");

        userService.updateUserIsEnabled(userId, userIsEnabled);

        return userIsEnabled;
    }

    /**
     * @POST 계정 탈퇴 수정
     */
    @UserRole(type = UserRole.Type.ADMIN)
    @PostMapping("/userDeleteEdit")
    public @ResponseBody boolean userDeleteEditPOST(@RequestParam String userId, @RequestParam boolean userIsDeleted) {
        logger.info("userDeleteEditPOST()");

        userService.updateUserIsDeleted(new User(userId), userIsDeleted, true); // 관리자 권한 계정 탈퇴

        return userIsDeleted;
    }
}