package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.global.security.jwt.Token;
import com.flab.skilltrademarket.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final TokenProvider tokenProvider;

    public Token createToken(String username) {
        return tokenProvider.generateToken(username);
    }
}
