package com.demo.user;

import com.demo.global.utils.UserAuth;
import com.demo.model.LoginDTO;
import com.demo.model.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller("userController")
@RequestMapping(value = "/user/*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService;

    /**
     * GET Request: 회원가입 폼
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @GetMapping(value = "/joinForm")
    public void getJoinForm(Model model) throws Exception {
        logger.info("getJoinForm()");

        model.addAttribute("joinForm", new UserDTO());
    }

    /**
     * POST Request: 폼 유효성 검사/중복 검사/회원가입 처리
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @PostMapping(value = "/join")
    public String postJoin(@ModelAttribute("joinForm") @Valid UserDTO userDTO, BindingResult result, RedirectAttributes rttr) throws Exception {
        logger.info("postJoin()");

        // 유효성 검증 실패/중복 검사 실패/회원가입 성공
        if (result.hasErrors()) {
            logger.info(result.toString());
            return "user/joinForm";

        } else if (postCheckId(userDTO.getUserId()) != 0
                || postCheckSno(userDTO.getUserSno()) != 0
                || postCheckEmail(userDTO.getUserEmail()) != 0) {
            return "user/joinForm";

        } else {
            userService.join(userDTO);
            rttr.addFlashAttribute("msg", "회원가입이 완료되었습니다. 가입 승인 이후 로그인 가능합니다.");
            return "redirect:/";
        }
    }

    /**
     * GET Request: 로그인 폼
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @GetMapping(value = "/loginForm")
    public void getLoginForm(Model model) throws Exception {
        logger.info("getLoginForm()");

        model.addAttribute("loginForm", new UserDTO());
    }

    /**
     * POST Request: 폼 유효성 검사 및 로그인 처리
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @PostMapping(value = "/login")
    public String postLogin(@ModelAttribute("loginForm") LoginDTO loginDTO, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
        logger.info("postLogin()");

        UserDTO userDTO = userService.login(loginDTO);

        if (userDTO == null) {
            // 로그인 실패 (ID/PW 불일치)
            request.getSession().setAttribute("loggedUser", null);
            rttr.addFlashAttribute("msg", "ID 또는 비밀번호를 확인해주세요.");
            return "redirect:/user/loginForm";

        } else if (userDTO.getUserEnabled() == 0) {
            // 로그인 실패 (미승인 사용자)
            request.getSession().setAttribute("loggedUser", null);
            rttr.addFlashAttribute("msg", "가입 승인이 되지 않은 계정입니다.");
            return "redirect:/user/loginForm";

        } else {
            request.getSession().setAttribute("loggedUser", userDTO);
            rttr.addFlashAttribute("msg", "환영합니다! " + userDTO.getUserId() + "님 로그인 되었습니다.");
            return "redirect:/";
        }
    }

    /**
     * GET Request: 로그아웃 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/logout")
    public String postLogout(RedirectAttributes rttr, HttpServletRequest request) throws Exception {
        logger.info("postLogout()");

        UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("loggedUser");
        rttr.addFlashAttribute("msg", loggedUser.getUserId() + "님 로그아웃 되었습니다.");
        request.getSession().removeAttribute("loggedUser");

        return "redirect:/";
    }

    /**
     * GET Request: 회원정보 조회
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/userDetail")
	public void getUserDetail(HttpServletRequest request, Model model) throws Exception {
	    logger.info("getUserDetail()");

	    UserDTO userDTO = (UserDTO) request.getSession().getAttribute("loggedUser");
	    model.addAttribute("selectedUser", userDTO);
	}

    /**
     * GET Request: 회원정보 수정 폼
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/modifyInfoForm")
	public void getModifyInfoForm(HttpServletRequest request, Model model) throws Exception {
	    logger.info("getModifyInfoForm()");

	    UserDTO userDTO = (UserDTO) request.getSession().getAttribute("loggedUser");
	    model.addAttribute("modifyInfoForm", userDTO);
	}

    /**
     * POST Request: 회원정보 수정 처리
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @PostMapping(value = "/modifyInfo")
    public String postModifyInfo(@ModelAttribute("modifyInfoForm") @Valid UserDTO userDTO, BindingResult result, HttpServletRequest request) throws Exception {
        logger.info("postModifyInfo()");

        UserDTO preUserDTO = (UserDTO) request.getSession().getAttribute("loggedUser");
        userDTO.setUserId(preUserDTO.getUserId());
        userDTO.setUserPw(preUserDTO.getUserPw());
        
        UserDTO newUserDTO = userService.modify(userDTO);
        request.getSession().removeAttribute("loggedUser");
        request.getSession().setAttribute("loggedUser", newUserDTO);
        return "redirect:/user/userDetail";
    }

    /**
     * GET Request: 보안설정 변경 폼 (비밀번호 수정 폼)
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @GetMapping(value = "/modifySecurityForm")
    public void getModifySecurityForm(HttpServletRequest request, Model model) throws Exception {
        logger.info("getModifySecurityForm()");

        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("loggedUser");
        model.addAttribute("modifySecurityForm", userDTO);
    }

    /**
     * POST Request: 보안설정 변경 처리 (비밀번호 수정)
     */
    @UserAuth(role = UserAuth.Roles.USER)
    @PostMapping(value = "/modifySecurity")
    public String postModifySecurity(@ModelAttribute("modifyForm") @Valid UserDTO userDTO, BindingResult result, HttpServletRequest request) throws Exception {
        logger.info("postModifySecurity()");

        UserDTO preUserDTO = (UserDTO) request.getSession().getAttribute("loggedUser");
        String userPw = userDTO.getUserPw();
        userDTO.setUserDTO(preUserDTO);
        userDTO.setUserPw(userPw);
        
        UserDTO newUserDTO = userService.modifySecurity(userDTO);
        request.getSession().removeAttribute("loggedUser");
        request.getSession().setAttribute("loggedUser", newUserDTO);
        return "redirect:/user/userDetail";
    }

    /**
     * POST Request: ID 중복 검사 (Ajax)
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @PostMapping(value = "/checkId")
    public @ResponseBody int postCheckId(String userId) throws Exception {
        logger.info("postCheckId()");

        return userService.checkId(userId);
    }

    /**
     * POST Request: 학번 중복 검사 (Ajax)
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @PostMapping(value = "/checkSno")
    public @ResponseBody int postCheckSno(String userSno) throws Exception {
        logger.info("postCheckSno()");

        return userService.checkSno(userSno);
    }

    /**
     * POST Request: 이메일 중복 검사 (Ajax)
     */
    @UserAuth(role = UserAuth.Roles.GUEST)
    @PostMapping(value = "/checkEmail")
    public @ResponseBody int postCheckEmail(String userEmail) throws Exception {
        logger.info("postCheckEmail()");

        return userService.checkEmail(userEmail);
    }
}