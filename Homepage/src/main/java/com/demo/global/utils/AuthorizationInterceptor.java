package com.demo.global.utils;

import com.demo.user.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.demo.global.utils.UserAuth.Roles.*;
import static com.demo.global.utils.UserAuth.Roles.ADMIN;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Inject
    private AuthorizationHelper authorizationHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 클래스 또는 메소드에 선언된 권한(어노테이션)을 가져온다.
        UserAuth auth = getAuthority(handler);

        if (auth == null) {
            // 요구 권한: 없음
            logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: 없음)");
            return true;

        } else {
            // 세션에 저장된 사용자 정보를 가져온다.
            UserDTO loggedUser = (UserDTO) request.getSession().getAttribute("loggedUser");

            // 요구되는 권한을 짧은 변수명으로 치환한다.
            String requiredRole = auth.role().toString();

            switch (auth.role()) {

                case ADMIN:
                    String userAuth = loggedUser.getUserAuthority(); // GUEST 일 경우, loggedUser 데이터가 없으므로 Null. 따라서 ADMIN 또는 USER 케이스 내부에 작성한다.

                    if (requiredRole.equals(userAuth)) {
                        // 요구 권한: ADMIN, 사용자 권한: ADMIN
                        logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + " / 사용자 권한: " + userAuth + ")");
                        return true;
                    } else {
                        logger.info("권한 검사: 해당 요청은 ADMIN 권한이 필요합니다. (요구 권한: " + requiredRole + " / 사용자 권한: " + userAuth + ")");
                        response.sendRedirect(request.getContextPath() + "/error/403");
                        return false;
                    }

                case USER:
                    userAuth = loggedUser.getUserAuthority();

                    if (userAuth.equals(ADMIN.toString())) {
                        // 요구 권한: USER, 사용자 권한: ADMIN
                        logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + " / 사용자 권한: " + userAuth + ")");
                        return true;
                    } else if (requiredRole.equals(userAuth)) {
                        // 요구 권한: USER, 사용자 권한: USER
                        logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + " / 사용자 권한: " + userAuth + ")");
                        return true;
                    } else {
                        logger.info("권한 검사: 해당 요청은 USER 권한이 필요합니다. (요구 권한: " + requiredRole + " / 사용자 권한: " + userAuth + ")");
                        response.sendRedirect(request.getContextPath() + "/error/403");
                        return false;
                    }

                case GUEST:
                    logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + ")");
                    return true;

                default:
                    logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + ")");
                    return true;
            }
        }
    }

    private UserAuth getAuthority(Object handler) {
        return authorizationHelper.checkAuthorization(handler);
    }
}