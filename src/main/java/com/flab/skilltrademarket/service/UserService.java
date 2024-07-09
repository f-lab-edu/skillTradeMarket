package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.domain.user.dto.SignupRequest;
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
    private final TermService termService;
    public void checkDuplicateEmail(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new ApiException(ExceptionCode.DUPLICATE_EMAIL);
        }
    }

    public void checkDuplicateNickName(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new ApiException(ExceptionCode.DUPLICATE_NICKNAME);
        }
    }

    @Transactional
    public void save(SignupRequest signupRequest) {
        checkDuplicateEmail(signupRequest.email());
        checkDuplicateNickName(signupRequest.nickname());
        String encodedPassword = passwordEncoder.encode(signupRequest.password());
        User user = userRepository.save(signupRequest.toEntity(encodedPassword));
        termService.saveSignupTerm(user.getId(), signupRequest.termTypeList());
    }

}
