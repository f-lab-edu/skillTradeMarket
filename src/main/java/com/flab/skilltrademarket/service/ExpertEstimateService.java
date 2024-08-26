package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.estimate.ExpertEstimate;
import com.flab.skilltrademarket.domain.estimate.UserEstimate;
import com.flab.skilltrademarket.domain.estimate.dto.request.ExpertEstimateCreateRequest;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.repository.ExpertEstimateRepository;
import com.flab.skilltrademarket.repository.StoreRepository;
import com.flab.skilltrademarket.repository.UserEstimateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertEstimateService {
    private final ExpertEstimateRepository expertEstimateRepository;
    private final StoreRepository storeRepository;
    private final UserEstimateRepository userEstimateRepository;
    public void create(UserDetails user, ExpertEstimateCreateRequest createRequest) {

        if (!user.role().isExpert()) {
            throw new ApiException(ExceptionCode.NO_ACCESS_EXPERT);
        }

        Store store = storeRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));
        UserEstimate userEstimate = userEstimateRepository.findById(createRequest.userEstimateId())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_USER_ESTIMATE));

        ExpertEstimate expertEstimate = ExpertEstimateCreateRequest.toEntity(createRequest, userEstimate, store);

        expertEstimateRepository.save(expertEstimate);
    }
}