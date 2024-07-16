package com.flab.skilltrademarket.domain.phone.dto;

public record PhoneAuthResponse(int code) {

    public static PhoneAuthResponse from(int code){
        return new PhoneAuthResponse(code);
    }
}
