package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickName);
}
