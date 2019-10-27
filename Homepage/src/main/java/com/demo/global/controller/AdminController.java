package com.demo.global.controller;

import com.demo.user.dto.UserDTO;
import com.demo.user.service.UserService;
import com.demo.global.utils.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.inject.Inject;
import java.util.List;

/**
 * 클래스 전체 권한: ADMIN
 */
@UserAuth(role = UserAuth.Roles.ADMIN)
@Controller("adminController")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Inject
    private UserService userService;

    /*--------------------------------------------------- 관리자 홈 ---------------------------------------------------*/

    /**
     * GET Request: 관리자 인덱스 페이지
     */
    @GetMapping(value = "/admin/index")
    public String getAdminIndex() throws Exception {
        logger.info("getAdminIndex()");

        return "admin/index";
    }

    /**
     * POST Request: 관리자 인덱스 페이지
     */
    @PostMapping(value = "/admin/index")
    public String postAdminIndex() throws Exception {
        logger.info("postAdminIndex()");

        return "admin/index";
    }
    
    /*--------------------------------------------------- 회원 관리 ---------------------------------------------------*/

    /**
     * GET Request: 회원 목록 조회
     */
    @GetMapping(value = "/admin/userList")
    public void getUserList(@RequestParam(value = "msg", defaultValue = "계정 관리 시, 주의해주세요.") String msg, Model model) throws Exception {
        logger.info("getUserList()");

        List<UserDTO> userList = userService.showUserList();

        model.addAttribute("userList", userList);
        model.addAttribute("msg", msg);
    }

    /**
     * GET Request: 회원 상세 조회
     */
    @GetMapping(value = "/admin/userDetail/{userId}")
    public String getUserDetail(@PathVariable("userId") String userId, Model model) throws Exception {
        logger.info("getUserDetail()");

        UserDTO selectedUser = userService.showUserDetail(userId);
        model.addAttribute("selectedUser", selectedUser);

        return "admin/userDetail";
    }

    /**
     * GET / POST Request: 계정 활성화 (회원가입 승인)
     */
    @RequestMapping(value = "/admin/enableUser/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String requestEnableUser(@PathVariable("userId") String userId, RedirectAttributes rttr) throws Exception {
        logger.info("requestEnableUser()");

        userService.modifyUserEnabled(userId, 1);
        rttr.addAttribute("msg", userId + " 계정을 활성화하였습니다.");

        return "redirect:/admin/userList";
    }

    /**
     * GET / POST Request: 계정 비활성화
     */
    @RequestMapping(value = "/admin/unEnableUser/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String requestUnEnableUser(@PathVariable("userId") String userId, RedirectAttributes rttr) throws Exception {
        logger.info("requestUnEnableUser()");

        userService.modifyUserEnabled(userId, 0);
        rttr.addAttribute("msg", userId + " 계정을 비활성화하였습니다.");

        return "redirect:/admin/userList";
    }

    /**
     * GET / POST Request: 계정 삭제
     */
    @RequestMapping(value = "/admin/deleteUser/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String requestDeleteUser(@PathVariable("userId") String userId, RedirectAttributes rttr) throws Exception {
        logger.info("requestDeleteUser()");

        userService.deleteUser(userId);
        rttr.addAttribute("msg", userId + " 계정을 삭제하였습니다.");

        return "redirect:/admin/userList";
    }

    /*-------------------------------------------------- 게시판 관리 --------------------------------------------------*/
    /*--------------------------------------------------- 대여 관리 ---------------------------------------------------*/

}