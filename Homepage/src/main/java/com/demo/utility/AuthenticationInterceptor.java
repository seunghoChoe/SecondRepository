package com.demo.utility;

import com.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 세션 기반의 인증 인터셉터 클래스
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("인증 검사(URL): " + request.getRequestURI());

        // 클래스 또는 메소드에 선언된 권한(어노테이션)을 가져온다.
        UserRole role = checkUserRoleType(handler);

        if (role == null || role.type() == UserRole.Type.GUEST) {
            logger.info("인증 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (접근 권한: 없음 또는 GUEST)");
            return true;
        }

        // 세션에 저장된 사용자 정보를 가져온다.
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        // 로그인 여부 및 접근 권한 판별
        if (loggedUser == null) {
            logger.info("인증 검사: 해당 요청은 로그인이 필요합니다. 로그인 페이지로 이동합니다. (세션 없음)");
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;

        } else {
            logger.info("인증 검사: 해당 요청을 Interceptor 또는 Controller 로 전달합니다. (세션 있음)");
            return true;
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