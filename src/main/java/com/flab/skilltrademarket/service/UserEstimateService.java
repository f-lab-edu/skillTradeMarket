package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.estimate.UserEstimate;
import com.flab.skilltrademarket.domain.estimate.dto.request.UserEstimateCreateRequest;
import com.flab.skilltrademarket.domain.estimate.dto.response.UserEstimateListResponse;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.SubCategoryRepository;
import com.flab.skilltrademarket.repository.UserEstimateRepository;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserEstimateService {
    private final UserEstimateRepository  userEstimateRepository;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;
    @Transactional
    public void create(Long userId, Long subCategoryId, UserEstimateCreateRequest createRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));

        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));

        UserEstimate userEstimate = UserEstimateCreateRequest.toEntity(user, subCategory, createRequest);

        userEstimateRepository.save(userEstimate);
    }

    public UserEstimateListResponse findAllByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        List<UserEstimate> allUserEstimate = userEstimateRepository.findAllByUser(user);
        return new UserEstimateListResponse(allUserEstimate);
    }
}
