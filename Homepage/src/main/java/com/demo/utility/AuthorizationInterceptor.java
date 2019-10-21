package com.demo.utility;

import com.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.demo.utility.UserRole.Type.*;

/**
 * @Description: 세션 기반의 접근 제어 인터셉터 클래스
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("권한 검사(URL): " + request.getRequestURI());

        // 클래스 또는 메소드에 선언된 권한(어노테이션)을 가져온다.
        UserRole role = checkUserRoleType(handler);

        if (role == null) {
            logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: 없음)");
            return true;

        } else {
            // 세션에 저장된 사용자 정보를 가져온다.
            User loggedUser = (User) request.getSession().getAttribute("loggedUser");

            // 요구 권한을 String 타입으로 변환한다.
            String requiredRole = role.type().toString();

            switch (role.type()) {

                case ADMIN:
                    String userAuth = loggedUser.getUserRole(); // GUEST 일 경우, loggedUser 데이터가 없으므로 Null. 따라서 ADMIN 또는 USER 케이스 내부에 작성한다.

                    if (requiredRole.equals(userAuth)) {
                        // 요구 권한: ADMIN, 계정 권한: ADMIN
                        logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + " / 계정 권한: " + userAuth + ")");
                        return true;
                    } else {
                        logger.info("권한 검사: 해당 요청은 ADMIN 권한이 필요합니다. (요구 권한: " + requiredRole + " / 계정 권한: " + userAuth + ")");
                        response.sendRedirect(request.getContextPath() + "/error/403");
                        return false;
                    }

                case USER:
                    userAuth = loggedUser.getUserRole();

                    if (userAuth.equals(ADMIN.toString()) || requiredRole.equals(userAuth)) {
                        // 요구 권한: USER, 계정 권한: ADMIN
                        // 요구 권한: USER, 계정 권한: USER
                        logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + " / 계정 권한: " + userAuth + ")");
                        return true;
                    } else {
                        logger.info("권한 검사: 해당 요청은 USER 권한이 필요합니다. (요구 권한: " + requiredRole + " / 사용자 권한: " + userAuth + ")");
                        response.sendRedirect(request.getContextPath() + "/error/403");
                        return false;
                    }

                default:
                    logger.info("권한 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (요구 권한: " + requiredRole + ")");
                    return true;
            }
        }
    }

    /**
     * handler 객체의 타입을 확인하는 메소드 (handler 객체가 HandlerMethod 타입이 아닐 경우, 형변환 시 에러가 발생한다.)
     */
    private UserRole checkUserRoleType(Object handler) {
        if (handler instanceof HandlerMethod) {
            // handler 객체가 HandlerMethod 타입일 경우, 메소드에 선언된 UserRole 어노테이션을 가져온다.
            return ((HandlerMethod) handler).getMethodAnnotation(UserRole.class);
        } else {
            return null;
        }
    }
}