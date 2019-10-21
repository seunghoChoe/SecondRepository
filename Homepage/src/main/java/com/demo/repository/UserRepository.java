package com.demo.repository;

import com.demo.model.Criteria;
import com.demo.model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 계정 레포지토리 클래스
 */
@Repository
public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private static String namespace = "mappers.UserMapper";

    @Autowired
    private SqlSessionTemplate sql;

    // INSERT 작업                                                                                                      INSERT 작업

    /**
     * @INSERT: 계정 등록
     */
    public int insertUser(User user) {
        return sql.insert(namespace + ".insertUser", user);
    }

    // SELECT 작업                                                                                                      SELECT 작업

    /**
     * @SELECT: 전체 계정 개수 조회
     */
    public int countAllUser() {
        return sql.selectOne(namespace + ".countAllUser");
    }

    /**
     * @SELECT: 계정 목록 조회
     */
    public List<Map<String, Object>> selectUserList(Criteria criteria) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageStart", criteria.getPageStart());
        map.put("perPageNum", criteria.getPerPageNumber());
        return sql.selectList(namespace + ".selectUserList", map);
    }

    /**
     * @SELECT: 로그인 및 계정 상세 조회
     */
    public User selectUser(User user, boolean isLogin) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        if (isLogin) {
            map.put("isLogin", true);
            return sql.selectOne(namespace + ".selectUser", map);
        } else {
            map.put("isLogin", false);
            return sql.selectOne(namespace + ".selectUser", map);
        }
    }

    /**
     * @SELECT: ID 중복 검사
     */
    public int countUserByUserId(String userId) {
        return sql.selectOne(namespace + ".countUserByUserId", userId);
    }

    /**
     * @SELECT: 학번 중복 검사
     */
    public int countUserByUserNumber(String userNumber) {
        return sql.selectOne(namespace + ".countUserByUserNumber", userNumber);
    }

    /**
     * @SELECT: 이메일 중복 검사
     */
    public int countUserByUserEmail(String userEmail) {
        return sql.selectOne(namespace + ".countUserByUserEmail", userEmail);
    }

    // UPDATE 작업                                                                                                      UPDATE 작업

    /**
     * @UPDATE: 최근 로그인 일자 수정
     */
    public int updateUserUpdatedAt(String userId) {
        return sql.update(namespace + ".updateUserUpdatedAt", userId);
    }

    /**
     * @UPDATE: 계정 정보 수정
     */
    public int updateUser(User user) {
        return sql.update(namespace + ".updateUser", user);
    }

    /**
     * @UPDATE: 이름 수정
     */
    public int updateUserName(User user) {
        return sql.update(namespace + ".updateUserName", user);
    }

    /**
     * @UPDATE: 학번 수정
     */
    public int updateUserNumber(User user) {
        return sql.update(namespace + ".updateUserNumber", user);
    }

    /**
     * @UPDATE: 이메일 수정
     */
    public int updateUserEmail(User user) {
        return sql.update(namespace + ".updateUserEmail", user);
    }

    /**
     * @UPDATE: 비밀번호 수정
     */
    public int updateUserPassword(User user) {
        return sql.update(namespace + ".updateUserPassword", user);
    }

    /**
     * @UPDATE: 계정 활성 수정
     */
    public int updateUserIsEnabled(User user) {
        return sql.update(namespace + ".updateUserIsEnabled", user);
    }

    /**
     * @UPDATE: 계정 삭제 수정
     */
    public int updateUserIsDeleted(User user, boolean isAdmin) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("isAdmin", isAdmin);
        return sql.update(namespace + ".updateUserIsDeleted", map);
    }

    // DELETE 작업                                                                                                      DELETE 작업

}