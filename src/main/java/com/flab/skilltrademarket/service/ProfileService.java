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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        return ProfileResponse.from(user);
    }


    public void changeRole(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        user.changeRole();
        userRepository.save(user);
    }
}
