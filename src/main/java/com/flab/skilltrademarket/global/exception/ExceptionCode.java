package com.flab.skilltrademarket.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    // User
    NOT_FOUND_EMAIL("U001","이메일은 필수 입력 정보입니다.","이메일은 필수 입력 정보입니다.",HttpStatus.BAD_REQUEST),
    NOT_FOUND_NICKNAME("U002","닉네임은 필수 입력 정보입니다.","닉네임은 필수 입력 정보입니다.",HttpStatus.BAD_REQUEST),
    VALIDATE_EMAIL("U003","이메일 형식이 맞지 않습니다.","잘못된 형식입니다.",HttpStatus.BAD_REQUEST),
    VALIDATE_NICKNAME("U004","닉네임 형식이 맞지 않습니다.","잘못된 형식입니다.",HttpStatus.BAD_REQUEST),
    VALIDATE_PHONE_NUM("U005","전화번호 형식이 맞지 않습니다.","잘못된 형식입니다.",HttpStatus.BAD_REQUEST),
    LOGIN_FAIL("U006","아이디,비밀번호를 확인해 주세요.","아이디,비밀번호를 확인해 주세요.",HttpStatus.BAD_REQUEST),
    NOT_FOUND("U007","존재하지 않는 사용자입니다.","존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL("U011","중복된 이메일[%s]입니다.","사용할 수 없는 이메일입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_NICKNAME("U012","중복된 닉네임[%s]입니다.","사용할 수 없는 닉네임입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PW_CONFIRM("U013", "비밀번호가 일치하지 않습니다.", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_CHECKED_ESSENTIAL_TERM("U014","필수약관에 동의해야합니다.","필수약관에 동의하지 않았습니다.",HttpStatus.BAD_REQUEST),
    USER_AUTH_PHONE_NOT_VERIFY("U021","인증을 진행하지 않은 번호입니다.","인증을 진행하지 않은 번호입니다.",HttpStatus.BAD_REQUEST),
    USER_AUTH_PHONE_CODE_DIFF("U022","인증번호가 일치하지 않습니다.","인증번호가 일치하지 않습니다.",HttpStatus.BAD_REQUEST),

    //
    COMMON_SYSTEM_ERROR("C001","시스템 에러입니다.","시스템 에러입니다.",HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("C002","인증이 필요합니다.","인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("C003","만료된 토큰 입니다.","만료된 토큰 입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("C004","유효하지 않은 토큰 입니다.","유효하지 않은 토큰 입니다.", HttpStatus.UNAUTHORIZED),

    // Category
    DUP_CAT_NAME("D001","중복된 카테고리명[%s]입니다.","중복된 카테고리명 입니다..",HttpStatus.BAD_REQUEST),
    NOT_FOUND_CAT("D002","해당 카테고리를 찾을 수 없습니다.","해당 카테고리를 찾을 수 없습니다.",HttpStatus.NOT_FOUND),
    NOT_FOUND_SUB_CAT("D003","해당 하위 카테고리를 찾을 수 없습니다.","해당 하위 카테고리를 찾을 수 없습니다.",HttpStatus.NOT_FOUND),
    DUP_SUB_CAT_NAME("D004","중복된 하위 카테고리명[%s] 입니다.","중복된 하위 카테고리명 입니다.",HttpStatus.BAD_REQUEST),

    //Expert
    NOT_FOUND_EXPERT("E001","존재하지 않는 고수입니다.","존재하지 않는 고수입니다.",HttpStatus.NOT_FOUND),
    DUP_STORE_NAME("E002", "중복된 상점명[%s]입니다.", "중복된 상점명입니다.", HttpStatus.BAD_REQUEST),
    NO_ACCESS_EXPERT("E003", "일반 사용자는 접근할 수 없습니다.", "일반 사용자는 접근할 수 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_EXPERT_SKILL("E004", "존재하지 않는 스킬입니다.", "존재하지 않는 스킬입니다.", HttpStatus.NOT_FOUND);
    private final String code;
    private final String internalMessage;
    private final String externalMessage;
    private final HttpStatus httpStatus;

}
