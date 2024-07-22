package com.flab.skilltrademarket.domain.phone;

import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.utils.PhoneAuthCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RedisPhoneAuth implements PhoneAuth{
    private final RedisService redisService;
    @Value("${spring.datasource.redis.validate-in-seconds}")
    private Long CODE_EXPIRATION_TIME;

    private static final String SMS_PREFIX = "sms:";
    @Override
    public int issueVerificationCode(String phone) {
        String redisKey = getRedisKey(phone);
        int code = PhoneAuthCodeGenerator.generateAuthCode();
        redisService.set(redisKey,new PhoneAuthEntity(code), CODE_EXPIRATION_TIME);
        return code;
    }

    @Override
    public boolean confirmPhoneAuthCode(String phone, int code) {
        String redisKey = getRedisKey(phone);
        Optional<PhoneAuthEntity> storedEntity = redisService.get(redisKey, PhoneAuthEntity.class);
        if (!storedEntity.isPresent()) {
            throw new ApiException(ExceptionCode.USER_AUTH_PHONE_NOT_VERIFY);
        }
        PhoneAuthEntity phoneAuthEntity = storedEntity.get();

        if (!phoneAuthEntity.isMatchCode(code)) {
            throw new ApiException(ExceptionCode.USER_AUTH_PHONE_CODE_DIFF);
        }
        redisService.set(redisKey, phoneAuthEntity, CODE_EXPIRATION_TIME);
        return true;
    }

    @Override
    public boolean isVerifiedCode(String phone) {
        String redisKey = getRedisKey(phone);
        Optional<PhoneAuthEntity> phoneAuthEntity = redisService.get(redisKey, PhoneAuthEntity.class);
        return  phoneAuthEntity.isPresent() && phoneAuthEntity.get().isVerification();
    }

    private String getRedisKey(String phone) {
        return SMS_PREFIX + phone;
    }
}
