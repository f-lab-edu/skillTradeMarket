package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickName);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}
