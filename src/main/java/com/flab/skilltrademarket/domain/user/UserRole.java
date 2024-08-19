package com.flab.skilltrademarket.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("USER", UserRole::getExpert),
    EXPERT("EXPERT", UserRole::getUser),
    ADMIN("ADMIN", UserRole::getAdmin);
    private final String role;
    private final Supplier<UserRole> changeRole;


    private static UserRole getExpert() {
        return UserRole.EXPERT;
    }

    private static UserRole getAdmin() {
        return UserRole.ADMIN;
    }

    private static UserRole getUser() {
        return UserRole.USER;
    }

    public boolean isExpert() {
        return this == EXPERT;
    }
}
