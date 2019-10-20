package com.demo.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginDTO {

    private static final long serialVersionUID = 1L;

    @Pattern(regexp="^[a-z0-9][a-z0-9_\\-]{4,16}$", message = "ID 형식이 맞지 않습니다.")
    // ID: 영문 소문자, 숫자와 특수기호(_),(-)만 허용, 5~15자 -> 네이버에서 사용 중인 정규식 글자수 변형
    private String userId;

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호 형식이 맞지 않습니다.")
    // 비밀번호: 8~15자, 최소 하나의 문자, 최소 하나의 숫자, 최소 하나의 특수 문자
    private String userPw;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                '}';
    }

    public LoginDTO createLoginDTO() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUserId(userId);
        loginDTO.setUserPw(userPw);

        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        userId = loginDTO.getUserId();
        userPw = loginDTO.getUserPw();
    }
}
