package com.demo.user.service;

import com.demo.user.dao.UserDAO;
import com.demo.user.dto.LoginDTO;
import com.demo.user.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDao;

    @Override
    public int checkId(String userId) throws Exception {
        return userDao.selectUserId(userId);
    }

    @Override
    public int checkSno(String userSno) throws Exception {
        return userDao.selectUserSno(userSno);
    }

    @Override
    public int checkEmail(String userEmail) throws Exception {
        return userDao.selectUserEmail(userEmail);
    }

    @Override
    public int join(UserDTO userDTO) throws Exception {
        return userDao.join(userDTO);
    }

    @Override
    public UserDTO login(LoginDTO loginDTO) throws Exception {
        return userDao.login(loginDTO);
    }

	@Override
	public UserDTO modify(@Valid UserDTO userDTO) throws Exception {
		userDao.modify(userDTO);
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUserId(userDTO.getUserId());
		loginDTO.setUserPw(userDTO.getUserPw());
		return login(loginDTO);
	}

	@Override
	public UserDTO modifySecurity(@Valid UserDTO userDTO) throws Exception {
		userDao.modifySecurity(userDTO);
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUserId(userDTO.getUserId());
		loginDTO.setUserPw(userDTO.getUserPw());
		return login(loginDTO);
	}

    @Override
    public List<UserDTO> showUserList() throws Exception {
        return userDao.selectUserList();
    }

    @Override
    public UserDTO showUserDetail(String userId) throws Exception {
        return userDao.selectUserById(userId);
    }

    @Override
    public int modifyUserEnabled(String userId, int userEnabled) throws Exception {
        return userDao.updateUserEnabled(userId, userEnabled);
    }

    @Override
    public int deleteUser(String userId) throws Exception {
        return userDao.deleteUser(userId);
    }
}