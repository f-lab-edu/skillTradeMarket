package com.flab.skilltrademarket.domain.user.dto;

import com.flab.skilltrademarket.domain.user.User;

public record UserInfo(
        Long id,
        String email,
        String nickname,
        String phone

) {
    public static UserInfo of(User user) {
        return new UserInfo(user.getId(), user.getEmail(), user.getNickname(), user.getPhone());
    }
}
