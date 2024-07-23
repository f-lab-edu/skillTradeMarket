package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.phone.RedisPhoneAuth;
import com.flab.skilltrademarket.domain.phone.dto.PhoneAuthResponse;
import com.flab.skilltrademarket.domain.phone.dto.PhoneConfirmRequest;
import com.flab.skilltrademarket.domain.phone.dto.PhoneVerifyRequest;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhoneAuthController {

    private final RedisPhoneAuth redisPhoneAuth;

    @PostMapping("/stm/phone-verification")
    public CommonResponse<PhoneAuthResponse> verifyPhone(@RequestBody PhoneVerifyRequest phoneVerifyRequest) {
        int verifyCode = redisPhoneAuth.issueVerificationCode(phoneVerifyRequest.phoneNum());
        return CommonResponse.success(PhoneAuthResponse.from(verifyCode));
    }

    @PostMapping("/stm/phone-confirm")
    public void confirmPhone(@RequestBody PhoneConfirmRequest phoneConfirmRequest) {
        boolean isConfirmed = redisPhoneAuth.confirmPhoneAuthCode(phoneConfirmRequest.phoneNum(), phoneConfirmRequest.code());
        if (!isConfirmed) {
            throw new ApiException(ExceptionCode.USER_AUTH_PHONE_CODE_DIFF);
        }
    }
}
