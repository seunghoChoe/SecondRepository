package com.demo.global.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

public class AuthorizationHelper {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationHelper.class);

    protected UserAuth checkAuthorization(Object handler) {

        logger.info(handler.getClass().getName());

        // 클래스에 선언된 접근 권한을 가져온다.
        UserAuth classAuth = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(UserAuth.class);

        // 메소드에 선언된 접근 권한을 가져온다.
        UserAuth methodAuth = ((HandlerMethod) handler).getMethodAnnotation(UserAuth.class);

        // 클래스 / 메소드 중, 선택된 대상의 접근 권한을 할당한다.
        UserAuth auth;

        // 클래스에 선언된 접근 권한인지, 메소드에 선언된 접근 권한인지 판별한다.
        // 따라서 클래스에 선언하였을 경우 메소드에는 선언할 수 없고, 메소드에 선언하였을 경우 클래스에는 선언할 수 없다.
        if (classAuth == null) {
            auth = methodAuth;
        } else if (methodAuth == null) {
            auth = classAuth;
        } else {
            auth = null;
        }

        // 접근 권한을 반환한다.
        return auth;
    }
}