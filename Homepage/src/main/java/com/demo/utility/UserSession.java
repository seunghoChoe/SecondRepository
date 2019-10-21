package com.demo.utility;

import com.demo.model.User;
import javax.servlet.http.HttpServletRequest;
import static com.demo.utility.Constants.*;

/**
 * @Description: 세션 유틸리티 클래스
 */
public class UserSession {

    /**
     * @COMMON: 세션에 계정 정보 등록/수정하기
     */
    public static User setUserSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute(LOGGED_USER, user);
        return user;
    }

    /**
     * @COMMON: 세션에서 현재 로그인 계정 정보 가져오기 (그외 세션이 필요할 경우, 세션 이름을 매개변수로 추가하여 사용한다.)
     */
    public static User getUserSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(LOGGED_USER);
    }

    /**
     * @COMMON: 세션에서 계정 정보 삭제하기
     */
    public static void deleteUserSession(HttpServletRequest request) {
        request.getSession().removeAttribute(LOGGED_USER);
    }
}