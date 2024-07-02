package com.flab.skilltrademarket.global.security.encryption;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class SHA256Encoder implements PasswordEncoder{

    /**
     * rawPassword 를 SHA-256으로 암호화
     * @param rawPassword : 비밀번호
     * @return 암호화된 비밀번호
     */
    @Override
    public String encode(String rawPassword) {
        validateRawPassword(rawPassword);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(rawPassword.getBytes());
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 알고리즘 암호화에 실패했습니다.", e);
        }
    }

    /**
     * rawPassword 와 encodedPassword 일치 여부
     * @param rawPassword
     * @param encodedPassword
     * @return 일치 여부
     */
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        validateRawPassword(rawPassword);

        if (StringUtils.isEmpty(encodedPassword)) {
            log.warn("암호화된 비밀번호가 null 또는 빈 값 입니다.");
            return false;
        }
        return encode(rawPassword).equals(encodedPassword);
    }


    /**
     * rawPassword null 인지 확인
     * @param rawPassword
     */
    private void validateRawPassword(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("비밀번호는 null 일 수 없습니다.");
        }
    }

    /**
     * 바이트 배열을 16진수로 문자열로 변환
     * @param hashedBytes 변환할 바이트 배열
     * @return 16진수 문자열
     */
    private String bytesToHex(byte[] hashedBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
