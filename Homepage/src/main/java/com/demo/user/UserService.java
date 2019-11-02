package com.demo.user;

import javax.validation.Valid;

import com.demo.model.LoginDTO;
import com.demo.model.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * ID 및 학번, 이메일 중복체크
     */
    public int checkId(String userId) throws Exception;
    public int checkSno(String userSno) throws Exception;
    public int checkEmail(String userEmail) throws Exception;

    /**
     * 회원가입 (회원가입 시, 입력된 데이터(JoinForm 인스턴스)를 DB 에 저장)
     */
    public int join(UserDTO userDTO) throws Exception;

    /**
     * 로그인 (로그인 시, 입력된 데이터(LoginForm 인스턴스)를 사용하여 DB 로부터 데이터를 가져와 UserDTO 인스턴스를 생성한 후, 반환)
     */
    public UserDTO login(LoginDTO loginDTO) throws Exception;
    
    
	public UserDTO modify(@Valid UserDTO userDTO) throws Exception;
	public UserDTO modifySecurity(@Valid UserDTO userDTO) throws Exception;


    /**
     * 관리자 권한: 계정 목록, 계정 상세 조회
     */
    public List<UserDTO> showUserList() throws Exception;
    public UserDTO showUserDetail(String userId) throws Exception;

    /**
     * 관리자 권한: 계정 활성화/비활성화(가입 승인), 계정 삭제
     */
    public int modifyUserEnabled(String userId, int userEnabled) throws Exception;
    public int deleteUser(String userId) throws Exception;
}
