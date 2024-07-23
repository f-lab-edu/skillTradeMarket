package com.flab.skilltrademarket.global.security.service;

import com.flab.skilltrademarket.global.security.model.UserDetails;

public interface UserDetailsService {

    UserDetails loadByUsername(String username);
}
