package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.phone.RedisPhoneAuth;
import com.flab.skilltrademarket.domain.phone.dto.PhoneAuthResponse;
import com.flab.skilltrademarket.domain.phone.dto.PhoneConfirmRequest;
import com.flab.skilltrademarket.domain.phone.dto.PhoneVerifyRequest;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "핸드폰 인증", description = "핸드폰 인증번호 인증, 확인 API")
@RestController
@RequiredArgsConstructor
public class PhoneAuthController {

    private final RedisPhoneAuth redisPhoneAuth;

    @PostMapping("/stm/phone-verification")
    @Operation(summary = "핸드폰 인증번호 받기",description = "핸드폰 인증합니다.")
    @ApiResponse(responseCode = "200",description = "핸드폰 인증번호 받기에 성공하였습니다.")
    public CommonResponse<PhoneAuthResponse> verifyPhone(@RequestBody PhoneVerifyRequest phoneVerifyRequest) {
        int verifyCode = redisPhoneAuth.issueVerificationCode(phoneVerifyRequest.phoneNum());
        return CommonResponse.success(PhoneAuthResponse.from(verifyCode));
    }

    @PostMapping("/stm/phone-confirm")
    @Operation(summary = "핸드폰 인증번호 확인",description = "핸드폰 인증번호를 학인합니다.")
    @ApiResponse(responseCode = "200",description = "핸드폰 인증에 성공하였습니다.")
    public void confirmPhone(@RequestBody PhoneConfirmRequest phoneConfirmRequest) {
        boolean isConfirmed = redisPhoneAuth.confirmPhoneAuthCode(phoneConfirmRequest.phoneNum(), phoneConfirmRequest.code());
        if (!isConfirmed) {
            throw new ApiException(ExceptionCode.USER_AUTH_PHONE_CODE_DIFF);
        }
    }
}
