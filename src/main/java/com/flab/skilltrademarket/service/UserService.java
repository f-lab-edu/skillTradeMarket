package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.user.dto.UserDto;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.encryption.PasswordEncoder;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Boolean checkDuplicateEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Boolean checkDuplicateNickName(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public void save(UserDto userDto) {
        if (checkDuplicateEmail(userDto.email())) {
            throw new ApiException("이미 존재하는 이메일[%s] 입니다.".formatted(userDto.email()), ExceptionCode.DUPLICATE_EMAIL);
        }
        if (checkDuplicateNickName(userDto.nickname())) {
            throw new ApiException("이미 존재하는 이메일[%s] 입니다.".formatted(userDto.nickname()), ExceptionCode.DUPLICATE_NICKNAME);
        }
        String encodedPassword = passwordEncoder.encode(userDto.password());
        userRepository.save(userDto.toEntity(encodedPassword));
    }

}
