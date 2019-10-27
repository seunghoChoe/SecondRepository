package com.demo.user.dao;

import com.demo.user.dto.LoginDTO;
import com.demo.user.dto.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    private static String namespace = "mappers.UserMapper";

    @Inject
    private SqlSession sql;

    @Override
    public int selectUserId(String userId) throws Exception {
        return sql.selectOne(namespace + ".selectUserId", userId);
    }

    @Override
    public int selectUserSno(String userSno) throws Exception {
        return sql.selectOne(namespace + ".selectUserSno", userSno);
    }

    @Override
    public int selectUserEmail(String userEmail) throws Exception {
        return sql.selectOne(namespace + ".selectUserEmail", userEmail);
    }

    @Transactional
    @Override
    public int join(UserDTO userDTO) throws Exception {
        return sql.insert(namespace + ".join", userDTO);
    }

    @Override
    public UserDTO login(LoginDTO loginDTO) throws Exception {
        return sql.selectOne(namespace + ".login", loginDTO);
    }

	@Override
	public int modify(@Valid UserDTO userDTO) throws Exception {
		return sql.update(namespace + ".modify", userDTO);
	}

	@Override
	public int modifySecurity(@Valid UserDTO userDTO) throws Exception {
		return sql.update(namespace + ".modifySecurity", userDTO);
	}

    @Override
    public List<UserDTO> selectUserList() throws Exception {
        return sql.selectList(namespace + ".selectUserList");
    }

    @Override
    public UserDTO selectUserById(String userId) throws Exception {
        return sql.selectOne(namespace + ".selectUserById", userId);
    }

    @Transactional
    @Override
    public int updateUserEnabled(String userId, int userEnabled) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId);
        paramsMap.put("userEnabled", userEnabled);
        return sql.update(namespace + ".updateUserEnabled", paramsMap);
    }

    @Transactional
    @Override
    public int deleteUser(String userId) throws Exception {
        return sql.delete(namespace + ".deleteUser", userId);
    }
}