package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.domain.user.response.ProfileResponse;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    private final UserRepository userRepository;


    public ProfileResponse getUserProfile(Long id) {
        return userRepository.findById(id)
                .map(ProfileResponse::from)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));

    }


    public void changeRole(Long id) {
        userRepository.findById(id)
                .map(user -> {
                    user.changeRole();
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
    }
}
