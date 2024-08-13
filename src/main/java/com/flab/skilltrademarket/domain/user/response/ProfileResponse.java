package com.flab.skilltrademarket.domain.user.response;

import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.domain.user.UserRole;

public record ProfileResponse(
        String email,
        String nickName,
        String phone,
        UserRole role
) {
    public static ProfileResponse from(User user) {
        return new ProfileResponse(user.getEmail(), user.getNickname(), user.getPhone(), user.getUserRole());
    }
}
