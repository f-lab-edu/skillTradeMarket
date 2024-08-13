package com.flab.skilltrademarket.global.security.model;

import com.flab.skilltrademarket.domain.user.UserRole;

public record UserDetails(
        Long id,
        String userId,
        String name,
        UserRole role
){


}
