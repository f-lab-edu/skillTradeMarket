package com.flab.skilltrademarket.domain.user;

import lombok.Getter;
@Getter
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");
    private String role;
    UserRole(String role){
        this.role = role;
    }
}
