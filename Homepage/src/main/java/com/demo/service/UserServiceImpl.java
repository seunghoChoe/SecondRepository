package com.demo.service;

import com.demo.model.Criteria;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Description: 계정 서비스 클래스 (개발 완료 시, 인터페이스 클래스를 생성하도록 한다.)
 */
@Service
public class UserServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String USER_NAME_URI = "/user/nameEdit";
    private static final String USER_NUMBER_URI = "/user/numberEdit";
    private static final String USER_EMAIL_URI = "/user/emailEdit";
    private static final String USER_PASSWORD_URI = "/user/security";

    @Autowired
    private UserRepository userRepository;

    /**
     * @INSERT: 계정 등록
     */
    public int insertUser(User user) {
        return userRepository.insertUser(user);
    }

    /**
     * @SELECT: 전체 계정 개수 조회
     */
    public int countAllUser() {
        return userRepository.countAllUser();
    }

    /**
     * @SELECT: 계정 목록 조회
     */
    public List<Map<String, Object>> selectUserList(Criteria criteria) {
        return userRepository.selectUserList(criteria);
    }

    /**
     * @SELECT: 로그인 및 계정 상세 조회
     */
    public User selectUser(User user, boolean isLogin) {
        return userRepository.selectUser(user, isLogin);
    }

    /**
     * @SELECT: ID 중복 검사
     */
    public int countUserByUserId(String userId) {
        return userRepository.countUserByUserId(userId);
    }

    /**
     * @SELECT: 학번 중복 검사
     */
    public int countUserByUserNumber(String userNumber) {
        return userRepository.countUserByUserNumber(userNumber);
    }

    /**
     * @SELECT: 이메일 중복 검사
     */
    public int countUserByUserEmail(String userEmail) {
        return userRepository.countUserByUserEmail(userEmail);
    }

    /**
     * @UPDATE: 최근 로그인 일자 수정
     */
    public int updateUserUpdatedAt(String userId) {
        return userRepository.updateUserUpdatedAt(userId);
    }

    /**
     * @UPDATE: 계정 정보 수정
     */
    public int updateUser(String type, User user) {
        switch (type) {
            case USER_NAME_URI:
                return userRepository.updateUserName(user);
            case USER_NUMBER_URI:
                return userRepository.updateUserNumber(user);
            case USER_EMAIL_URI:
                return userRepository.updateUserEmail(user);
            case USER_PASSWORD_URI:
                return userRepository.updateUserPassword(user);
            default:
                return 0; // 참고: 업데이트 결과가 없을 때, MySQL 은 0 을 반환한다.
        }
    }

    /**
     * @UPDATE: 계정 활성 수정
     */
    public int updateUserIsEnabled(String userId, boolean userIsEnabled) {
        User user = new User();
        user.setUserId(userId);
        user.setUserIsEnabled(userIsEnabled);
        return userRepository.updateUserIsEnabled(user);
    }

    /**
     * @UPDATE: 계정 삭제 수정
     */
    public int updateUserIsDeleted(User user, boolean userIsDeleted, boolean isAdmin) {
        user.setUserIsDeleted(userIsDeleted); // 삭제/복구 값 재할당

        if (isAdmin) {
            return userRepository.updateUserIsDeleted(user, isAdmin);
        } else {
            return userRepository.updateUserIsDeleted(user, isAdmin);
        }
    }
}