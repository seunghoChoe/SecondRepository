package com.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserDTO {

   private static final long serialVersionUID = 1L;

   @Pattern(regexp="^[a-z0-9][a-z0-9_\\-]{4,16}$", message = "ID 형식이 맞지 않습니다.")
   // ID: 영문 소문자, 숫자와 특수기호(_),(-)만 허용, 5~15자 -> 네이버에서 사용 중인 정규식 글자수 변형
   private String userId;

   @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호 형식이 맞지 않습니다.")
   // 비밀번호: 8~15자, 최소 하나의 문자, 최소 하나의 숫자, 최소 하나의 특수 문자
   private String userPw;

   @Pattern(regexp="^[0-9]{8}$", message = "학번 형식이 맞지 않습니다.")
   // 학번: 0~9 정수만 허용(공백 비허용), 8자
   private String userSno;

   @Pattern(regexp="^[가-힣]{2,5}$", message = "이름 형식이 맞지 않습니다.")
   // 이름: 한글만 허용(자음 또는 모음 비허용, 공백 비허용), 2~5자
   private String userName;

   @Pattern(regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식이 맞지 않습니다.")
   // 이메일
   private String userEmail;

   private Date userRegDate; // 가입 일자
   private Date userLogDate; // 최근 로그인 일자
   private int userEnabled; // 사용자 인증 여부
   private String userAuthority; // 사용자 접근 권한

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

   public String getUserSno() {
      return userSno;
   }

   public void setUserSno(String userSno) {
      this.userSno = userSno;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserEmail() {
      return userEmail;
   }

   public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
   }

   public Date getUserRegDate() {
      return userRegDate;
   }

   public void setUserRegDate(Date userRegDate) {
      this.userRegDate = userRegDate;
   }

   public Date getUserLogDate() {
      return userLogDate;
   }

   public void setUserLogDate(Date userLogDate) {
      this.userLogDate = userLogDate;
   }

   public int getUserEnabled() {
      return userEnabled;
   }

   public void setUserEnabled(int userEnabled) {
      this.userEnabled = userEnabled;
   }

   public String getUserAuthority() {
      return userAuthority;
   }

   public void setUserAuthority(String userAuthority) {
      this.userAuthority = userAuthority;
   }

   @Override
   public String toString() {
      return "UserDTO{" +
              "userId='" + userId + '\'' +
              ", userPw='" + userPw + '\'' +
              ", userSno='" + userSno + '\'' +
              ", userName='" + userName + '\'' +
              ", userEmail='" + userEmail + '\'' +
              ", userRegDate=" + userRegDate +
              ", userLogDate=" + userLogDate +
              ", userEnabled=" + userEnabled +
              ", userAuthority='" + userAuthority + '\'' +
              '}';
   }

   public UserDTO createUserDTO() {
      UserDTO userDTO = new UserDTO();

      userDTO.setUserId(userId);
      userDTO.setUserPw(userPw);
      userDTO.setUserSno(userSno);
      userDTO.setUserName(userName);
      userDTO.setUserEmail(userEmail);
      userDTO.setUserRegDate(userRegDate);
      userDTO.setUserLogDate(userLogDate);
      userDTO.setUserEnabled(userEnabled);
      userDTO.setUserAuthority(userAuthority);

      return userDTO;
   }

   public void setUserDTO(UserDTO userDTO) {
      userId = userDTO.getUserId();
      userPw = userDTO.getUserPw();
      userSno = userDTO.getUserSno();
      userName = userDTO.getUserName();
      userEmail = userDTO.getUserEmail();
      userRegDate = userDTO.getUserRegDate();
      userLogDate = userDTO.getUserLogDate();
      userEnabled = userDTO.getUserEnabled();
      userAuthority = userDTO.getUserAuthority();
   }
}