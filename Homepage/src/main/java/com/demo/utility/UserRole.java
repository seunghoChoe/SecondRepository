package com.demo.utility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 접근 권한 어노테이션 클래스 (컨트롤러 메소드에만 사용할 수 있다.)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface UserRole {

    enum Type {
        // 관리자, 로그인 사용자, 비로그인 사용자 권한으로 구분한다.
        ADMIN, USER, GUEST
    }

    Type type() default Type.USER;
}