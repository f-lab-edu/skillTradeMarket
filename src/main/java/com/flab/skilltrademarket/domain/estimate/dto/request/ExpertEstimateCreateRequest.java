package com.flab.skilltrademarket.domain.estimate.dto.request;

import com.flab.skilltrademarket.domain.estimate.ExpertEstimate;
import com.flab.skilltrademarket.domain.estimate.UserEstimate;
import com.flab.skilltrademarket.domain.store.Store;

public record ExpertEstimateCreateRequest(
        Long userEstimateId,
        String description,
        int totalCost

) {
    public static ExpertEstimate toEntity(
            ExpertEstimateCreateRequest createRequest,
            UserEstimate userEstimate,
            Store store) {

        return ExpertEstimate.builder()
                .userEstimate(userEstimate)
                .store(store)
                .totalCost(createRequest.totalCost)
                .description(createRequest.description)
                .build();
    }
}


