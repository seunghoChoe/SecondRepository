package com.demo.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: 비밀번호 암호화 클래스
 */
public class UserPasswordEncoder implements PasswordEncoder {
    private static final Logger logger = LoggerFactory.getLogger(UserPasswordEncoder.class);

    private PasswordEncoder passwordEncoder;

    public UserPasswordEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 평문 암호화 메소드
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 평문, 암호문 비교 메소드
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}