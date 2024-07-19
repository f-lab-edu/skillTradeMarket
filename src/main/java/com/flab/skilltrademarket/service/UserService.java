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
            throw new ApiException(ExceptionCode.DUPLICATE_EMAIL,email);
        }
    }

    public void checkDuplicateNickName(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new ApiException(ExceptionCode.DUPLICATE_NICKNAME,log::info);
        }
    }

    @Transactional
    public void save(SignupRequest signupRequest) {
        saveValidated(signupRequest);
        String encodedPassword = passwordEncoder.encode(signupRequest.password());
        User user = userRepository.save(signupRequest.toEntity(encodedPassword));
        termService.saveSignupTerm(user.getId(), signupRequest.termTypeList());
    }

    private void saveValidated(SignupRequest signupRequest) {
        if (signupRequest.email() == null) {
            throw new ApiException(ExceptionCode.NOT_FOUND_EMAIL);
        }
        if (signupRequest.nickname() == null) {
            throw new ApiException(ExceptionCode.NOT_FOUND_NICKNAME);
        }
        checkDuplicateEmail(signupRequest.email());
        checkDuplicateNickName(signupRequest.nickname());
    }

}
