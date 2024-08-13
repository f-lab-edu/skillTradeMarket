package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.phone.RedisPhoneAuth;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.domain.user.dto.SignupRequest;
import com.flab.skilltrademarket.domain.user.dto.UserInfo;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.encryption.PasswordEncoder;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.service.UserDetailsService;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TermService termService;
    private final RedisPhoneAuth redisPhoneAuth;
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
        if(!redisPhoneAuth.isVerifiedCode(signupRequest.phone())){
            throw new ApiException(ExceptionCode.USER_AUTH_PHONE_NOT_VERIFY);
        }
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
    public UserInfo getLoginUser(String email, String encodedPassword) {
        User user = userRepository.findByEmailAndPassword(email, encodedPassword)
                .orElseThrow(() -> new ApiException(ExceptionCode.LOGIN_FAIL));

        return UserInfo.of(user);
    }

    @Override
    public UserDetails loadByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        return new UserDetails(user.getId(), user.getEmail(), user.getNickname(),user.getUserRole());
    }
}
