package com.demo.global.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.model.UserDTO;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Inject
    private AuthorizationHelper authorizationHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 클래스 또는 메소드에 선언된 권한(어노테이션)을 가져온다.
        UserAuth auth = getAuthority(handler);

        if (auth == null || auth.role() == UserAuth.Roles.GUEST) {
            // 지정된 권한 없음 또는 GUEST
            logger.info("인증 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: 없음 또는 GUEST)");
            return true;
        }

        // 세션에 저장된 사용자 정보를 가져온다.
        UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("loggedUser");

        // 로그인 여부 및 접근 권한 판별
        if (loggedUser == null) {
            logger.info("인증 검사: 해당 요청은 로그인이 필요합니다. 로그인 페이지로 이동합니다. (세션 없음)");
            response.sendRedirect(request.getContextPath() + "/user/loginForm");
            return false;

        } else {
            logger.info("인증 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (세션 있음)");
            return true;
        }
    }

    private UserAuth getAuthority(Object handler) {
        return authorizationHelper.checkAuthorization(handler);
    }
}