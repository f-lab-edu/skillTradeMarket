package com.flab.skilltrademarket.utils;

import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class ValidationUtil {
    private static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_NICK_NAME = Pattern.compile("^[A-Za-z0-9]{2,10}$");
    private static final Pattern VALID_PHONE_NUM = Pattern.compile("^01[016789]-\\d{3,4}-\\d{4}$");

    private static final Pattern VALID_STORE_NAME = Pattern.compile("^[가-힣a-zA-Z]+$");
    private static final Pattern VALID_RATING = Pattern.compile("^[1-5]$");

    public static void validateEmail(String email) {
        validate(email, VALID_EMAIL, ExceptionCode.VALIDATE_EMAIL);
    }

    public static void validateNickname(String nickname) {
        validate(nickname,VALID_NICK_NAME,ExceptionCode.VALIDATE_NICKNAME);
    }

    public static void validatePhoneNum(String phoneNum) {
        validate(phoneNum,VALID_PHONE_NUM,ExceptionCode.VALIDATE_PHONE_NUM);
    }

    public static void confirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ApiException(ExceptionCode.INVALID_PW_CONFIRM);
        }
    }

    public static void validateStoreName(String catName) {
        validate(catName,VALID_STORE_NAME,ExceptionCode.VALIDATE_STORE_NAME);
    }

    public static void validateRating(int rating) {
        validate(String.valueOf(rating), VALID_RATING, ExceptionCode.VALIDATE_PHONE_NUM);
    }
    private static void validate(String value, Pattern pattern, ExceptionCode exceptionCode) {
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new ApiException(exceptionCode);
        }
    }
}
