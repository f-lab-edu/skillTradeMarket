package com.flab.skilltrademarket.domain.phone;

import com.flab.skilltrademarket.utils.PhoneAuthCodeGenerator;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapPhoneAuth implements PhoneAuth {

    private static final Map<String, PhoneAuthEntity> store = new ConcurrentHashMap<>();

    @Override
    public int issueVerificationCode(String phone) {
        int code = PhoneAuthCodeGenerator.generateAuthCode();
        store.put(phone, new PhoneAuthEntity(code));
        return code;
    }

    @Override
    public boolean confirmPhoneAuthCode(String phone, int code) {
        PhoneAuthEntity phoneAuthEntity = store.get(phone);
        if (phoneAuthEntity == null) {
            throw new ApiException(ExceptionCode.USER_AUTH_PHONE_NOT_VERIFY);
        }
        return phoneAuthEntity.isMatchCode(code);
    }

    @Override
    public boolean isVerifiedCode(String phone) {
        return store.get(phone).isVerification();
    }

}
