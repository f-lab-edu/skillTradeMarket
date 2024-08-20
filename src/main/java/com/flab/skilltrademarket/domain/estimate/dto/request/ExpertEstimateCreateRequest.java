package com.flab.skilltrademarket.domain.estimate.dto.request;

import com.flab.skilltrademarket.domain.estimate.ExpertEstimate;
import com.flab.skilltrademarket.domain.estimate.UserEstimate;
import com.flab.skilltrademarket.domain.expert.Expert;

public record ExpertEstimateCreateRequest(
        Long userEstimateId,
        String description,
        int totalCost

) {
    public static ExpertEstimate toEntity(
            ExpertEstimateCreateRequest createRequest,
            UserEstimate userEstimate,
            Expert expert) {

        return ExpertEstimate.builder()
                .userEstimate(userEstimate)
                .expert(expert)
                .totalCost(createRequest.totalCost)
                .description(createRequest.description)
                .build();
    }
}


