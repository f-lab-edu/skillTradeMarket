package com.flab.skilltrademarket.domain.user.dto;

public record LoginRequest(
        String email,
        String password
) {
}
