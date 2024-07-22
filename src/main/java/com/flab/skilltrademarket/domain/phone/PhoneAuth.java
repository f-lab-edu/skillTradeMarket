package com.flab.skilltrademarket.domain.phone;

public interface PhoneAuth {

    int issueVerificationCode(String phone);

    boolean confirmPhoneAuthCode(String phone, int code);

    boolean isVerifiedCode(String phone);
}
