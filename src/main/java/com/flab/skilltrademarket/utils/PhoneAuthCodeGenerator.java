package com.flab.skilltrademarket.utils;

import java.security.SecureRandom;

public class PhoneAuthCodeGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static int generateAuthCode() {
        return 1000 + random.nextInt(900000);
    }
}
