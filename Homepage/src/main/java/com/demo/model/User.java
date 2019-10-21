package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 계정 모델 클래스
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Pattern(regexp="^[a-z0-9][a-z0-9_\\-]{4,16}$", message = "ID 형식이 맞지 않습니다.") // ID: 영문 소문자, 숫자와 특수기호(_),(-)만 허용, 5~15자
    private String userId; // 계정 ID

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호 형식이 맞지 않습니다.") // 비밀번호: 8~15자, 최소 하나의 문자, 최소 하나의 숫자, 최소 하나의 특수 문자
    private String userPassword; // 계정 비밀번호

    @Pattern(regexp="^[0-9]{8}$", message = "학번 형식이 맞지 않습니다.") // 학번: 0~9 정수만 허용(공백 비허용), 8자
    private String userNumber; // 사용자 학번

    @Pattern(regexp="^[가-힣]{2,5}$", message = "이름 형식이 맞지 않습니다.") // 이름: 한글만 허용(자음 또는 모음 비허용, 공백 비허용), 2~5자
    private String userName; // 사용자 이름

    @Pattern(regexp="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식이 맞지 않습니다.")
    private String userEmail; // 사용자 이메일

    private String userRole; // 계정 접근 권한
    private Date userCreatedAt; // 계정 가입 일자
    private Date userUpdatedAt; // 최근 로그인 일자
    private boolean userIsEnabled; // 계정 활성(승인) 여부(0: 활성X, 1: 활성O)
    private boolean userIsDeleted; // 계정 삭제 여부(0: 삭제X, 1: 삭제O)

    public User() { }

    // AdminController -> 계정 상세 조회, 계정 삭제 수정에서 사용
	public User(String userId) {
		this.userId = userId;
	}

	public User(String userId, String userPassword, String userNumber,
				String userName, String userEmail, String userRole,
				Date userCreatedAt, Date userUpdatedAt, boolean userIsEnabled, boolean userIsDeleted) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNumber = userNumber;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userCreatedAt = userCreatedAt;
        this.userUpdatedAt = userUpdatedAt;
        this.userIsEnabled = userIsEnabled;
        this.userIsDeleted = userIsDeleted;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(Date userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public Date getUserUpdatedAt() {
		return userUpdatedAt;
	}

	public void setUserUpdatedAt(Date userUpdatedAt) {
		this.userUpdatedAt = userUpdatedAt;
	}

	public boolean getUserIsEnabled() {
		return userIsEnabled;
	}

	public void setUserIsEnabled(boolean userIsEnabled) {
		this.userIsEnabled = userIsEnabled;
	}

	public boolean getUserIsDeleted() {
		return userIsDeleted;
	}

	public void setUserIsDeleted(boolean userIsDeleted) {
		this.userIsDeleted = userIsDeleted;
	}
}