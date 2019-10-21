package com.demo.controller;

import com.demo.model.User;
import com.demo.service.UserServiceImpl;
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
import static com.demo.utility.Constants.*;
import static com.demo.utility.UserSession.*;

/**
 * @Description: 계정 컨트롤러 클래스 (GET/POST 메소드만 사용한다.)
 */
@Controller
@RequestMapping("/user/*")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    /**
     * @GET: 계정 등록 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @GetMapping("/join")
    public void joinGET(Model model) {
        logger.info("joinGET()");

        model.addAttribute("user", new User());
    }

    /**
     * @POST: 계정 등록 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @PostMapping("/join")
    public String joinPOST(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes rttr) {
        logger.info("joinPOST()");

        if (result.hasErrors()) {
            logger.info(result.toString());
            return "user/join"; // 유효성 검증 실패

        } else if (idCheckPOST(user.getUserId()) != 0
                || numberCheckPOST(user.getUserNumber()) != 0
                || emailCheckPOST(user.getUserEmail()) != 0) {
            return "user/join"; // 중복 검사 실패

        } else {
            userService.insertUser(user);
            rttr.addFlashAttribute(SERVER_MESSAGE, "계정 등록이 완료되었습니다. 가입 승인 후 로그인 가능합니다.");
            return "redirect:/";
        }
    }

    /**
     * @GET: 로그인 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @GetMapping("/login")
    public String loginGET() {
        logger.info("loginGET()");

        return "user/login";
    }

    /**
     * @POST: 계정 로그인 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @PostMapping("/login")
    public String loginPOST(User user, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("loginPOST()");

        User loggedUser = userService.selectUser(user, true);

        if (loggedUser == null) {
            // 로그인 실패 (ID 또는 비밀번호 불일치)
            setUserSession(request, null);
            rttr.addFlashAttribute(SERVER_MESSAGE, "ID 또는 비밀번호를 확인하세요.");
            return "redirect:/user/login";

        } else if (! loggedUser.getUserIsEnabled()) {
            // 로그인 실패 (계정 미승인)
            setUserSession(request, null);
            rttr.addFlashAttribute(SERVER_MESSAGE, "가입 승인이 되지 않은 계정입니다.");
            return "redirect:/user/login";

        } else if (loggedUser.getUserIsDeleted()) {
            // 로그인 실패 (삭제된 계정)
            setUserSession(request, null);
            rttr.addFlashAttribute(SERVER_MESSAGE, "이미 탈퇴된 계정입니다.");
            return "redirect:/user/login";

        } else {
            setUserSession(request, loggedUser);
            userService.updateUserUpdatedAt(loggedUser.getUserId()); // 로그인 일자 수정
            rttr.addFlashAttribute(SERVER_MESSAGE, "환영합니다! " + loggedUser.getUserId() + "님, 로그인 되었습니다.");
            return "redirect:/";
        }
    }

    /**
     * @GET: 계정 로그아웃 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/logout")
    public String logoutGET(RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("logoutGET()");

        rttr.addFlashAttribute(SERVER_MESSAGE, getUserSession(request).getUserId() + "님, 로그아웃 되었습니다.");
        deleteUserSession(request); // 세션 삭제

        return "redirect:/";
    }

    /**
     * @GET: 계정 정보 조회 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/info")
    public String infoGET(HttpServletRequest request, Model model) {
        logger.info("infoGET()");

        model.addAttribute("user", getUserSession(request)); // 현재 세션 정보를 모델에 담는다.

        return "user/info";
    }

    /**
     * @GET: 계정 정보 수정 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/edit")
    public String editGET(HttpServletRequest request, Model model) {
        logger.info("editGET()");

        model.addAttribute("user", getUserSession(request)); // 현재 세션 정보를 모델에 담는다.

        return "user/edit";
    }

    /**
     * @POST: 계정 정보 수정 (폼에서 Input Hidden 값으로 사용자 ID 가 전달되어 사용할 수 있다.) (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping({"/nameEdit", "/numberEdit", "/emailEdit"})
    public String editPOST(@ModelAttribute("user") @Valid User editedUser, BindingResult result, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("editPOST()");

        if (result.hasErrors() || numberCheckPOST(editedUser.getUserNumber()) != 0 || emailCheckPOST(editedUser.getUserEmail()) != 0) { // SELECT 문에 null 값이 전달될 경우, 0 을 반환한다.
            rttr.addFlashAttribute(SERVER_MESSAGE, "입력 정보를 확인하세요.");
            return "redirect:/user/edit"; // 유효성 검증 실패

        } else {
            userService.updateUser(request.getServletPath(), editedUser); // editedUser 에는 사용자 ID 가 포함되어 있다.
            setUserSession(request, userService.selectUser(editedUser, false)); // 사용자 ID 로 계정 정보를 획득한 후, 세션에 반영한다.
            rttr.addFlashAttribute(SERVER_MESSAGE, "계정 정보 수정이 완료되었습니다.");
            return "redirect:/user/info";
        }
    }

    /**
     * @GET: 비밀번호 수정 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping(value = "/security")
    public String securityGET(Model model) {
        logger.info("securityGET()");

        model.addAttribute("user", new User()); // 세션을 모델에 담을 경우, 비밀번호 입력 필드에 세션의 비밀번호가 입력된 상태가 되므로 새로운 객체를 사용한다.

        return "user/security";
    }

    /*
     * @POST: 비밀번호 수정 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping(value = "/security")
    public String securityPOST(@ModelAttribute("user") @Valid User editedUser, BindingResult result, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("securityPOST()");

        if (result.hasErrors()) { // 유효성 검증 실패
            logger.info(result.toString());
            return "redirect:/user/security";

        } else {
            userService.updateUser(request.getServletPath(), editedUser); // editedUser 에는 사용자 ID 가 포함되어 있다.
            setUserSession(request, userService.selectUser(editedUser, false)); // 사용자 ID 로 계정 정보를 획득한 후, 세션에 반영한다.
            rttr.addFlashAttribute(SERVER_MESSAGE, "비밀번호 수정이 완료되었습니다.");
            return "redirect:/user/info";
        }
    }

    /**
     * @POST: 계정 탈퇴 (ID, 비밀번호 확인 후, 탈퇴 진행)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping(value = "/delete")
    public String deletePOST(User user, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("deletePOST()");

        User loggedUser = userService.selectUser(user, true);

        if (loggedUser == null) {
            rttr.addFlashAttribute(SERVER_MESSAGE, "ID 또는 비밀번호를 확인하세요.");
            return "redirect:/user/userDelete";

        } else {
            setUserSession(request, null); // 세션 삭제
            userService.updateUserIsDeleted(user, true, false); // 사용자 권한 계정 탈퇴
            rttr.addFlashAttribute(SERVER_MESSAGE, "계정이 탈퇴되었습니다.");
            return "redirect:/";
        }
    }

    /**
     * @POST: ID 중복 검사 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @PostMapping("/idCheck")
    public @ResponseBody int idCheckPOST(@RequestParam String userId) {
        logger.info("idCheckPOST()");

        return userService.countUserByUserId(userId);
    }

    /**
     * @POST: 학번 중복 검사 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @PostMapping("/numberCheck")
    public @ResponseBody int numberCheckPOST(@RequestParam String userNumber) {
        logger.info("numberCheckPOST()");

        return userService.countUserByUserNumber(userNumber);
    }

    /**
     * @POST: 이메일 중복 검사 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @PostMapping("/emailCheck")
    public @ResponseBody int emailCheckPOST(@RequestParam String userEmail) {
        logger.info("emailCheckPOST()");

        return userService.countUserByUserEmail(userEmail);
    }
}