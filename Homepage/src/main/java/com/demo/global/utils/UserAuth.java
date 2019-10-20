package com.demo.global.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 커스텀 어노테이션 (접근 권한)
 * @Retention: 세션을 처리하기 위한 어노테이션이므로, 실행 시(RUNTIME)에 동작하도록 옵션을 지정한다. (SOURCE(컴파일 이전), CLASS(컴파일 시점))
 * @Target: 어노테이션이 적용될 수 있는 Target 을 지정한다. (메소드)
 * (java.lang.annotation.ElementType 에 따르면 클래스(TYPE), 변수, 매개변수, 생성자 등에도 지정할 수 있음.)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface UserAuth {

    // 권한은 ADMIN, USER, ALL 로 구분한다. (필요 시, 추가하여 사용할 수 있다.)
    public enum Roles { ADMIN, USER, GUEST }

    // 클래스 또는 메소드 위에 @UserAuth(role = UserAuth.Roles.ADMIN) 과 같이 작성하여 커스텀 어노테이션을 사용할 수 있다.
    // default Roles.USER (@UserAuth 의 기본 값을 USER 로 지정한다.)
    public Roles role() default Roles.USER;
    // public Roles[] role() default Roles.USER;
}



/**
 * 클래스 전체에 권한을 지정할 경우 -> 클래스 내부의 각 메소드에 권한을 지정할 수 없음.
 * 메소드에 권한을 지정할 경우 -> 클래스 전체에 권한을 지정할 수 없음.
 *
 * @UserAuth(role = UserAuth.Roles.GUEST) 최소 요구 권한이 GUEST (GUEST 는 비회원이므로 별도로 GUEST 권한을 users 테이블에 저장할 필요 없음)
 * @UserAuth(role = UserAuth.Roles.USER) 최소 요구 권한이 USER (USER 권한은 GUEST 권한도 포함)
 * @UserAuth(role = UserAuth.Roles.ADMIN) 최소 요구 권한이 ADMIN (ADMIN 권한은 USER/GUEST 권한도 포함)
 */