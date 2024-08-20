package com.flab.skilltrademarket.domain.estimate.dto.response;

import com.flab.skilltrademarket.domain.estimate.UserEstimate;

import java.util.List;

public record UserEstimateListResponse(
        List<UserEstimate> userEstimateResponseList

) {
}
