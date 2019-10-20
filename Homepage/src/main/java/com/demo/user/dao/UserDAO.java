package com.demo.user.dao;

import com.demo.user.dto.LoginDTO;
import com.demo.user.dto.UserDTO;

import javax.validation.Valid;
import java.util.List;

public interface UserDAO {

    /**
     * ID 및 학번, 이메일 중복체크
     */
    public int selectUserId(String userId) throws Exception;
    public int selectUserSno(String userSno) throws Exception;
    public int selectUserEmail(String userEmail) throws Exception;

    /**
     * 회원가입 (가입 시, 사용한 데이터를 UserDTO 인스턴스로 생성)
     */
    public int join(UserDTO userDTO) throws Exception;

    /**
     * 로그인 (로그인 시, 사용한 로그인 데이터를 LoginDTO 인스턴스로 생성)
     */
    public UserDTO login(LoginDTO loginDTO) throws Exception;
    
    
	public int modify(@Valid UserDTO userDTO) throws Exception;
	public int modifySecurity(@Valid UserDTO userDTO) throws Exception;


    /**
     * 관리자 권한: 계정 목록, 계정 상세 조회
     */
    public List<UserDTO> selectUserList() throws Exception;
    public UserDTO selectUserById(String userId) throws Exception;

    /**
     * 관리자 권한: 계정 활성화/비활성화(가입 승인), 계정 삭제
     */
    public int updateUserEnabled(String userId, int userEnabled) throws Exception;
    public int deleteUser(String userId) throws Exception;
}
