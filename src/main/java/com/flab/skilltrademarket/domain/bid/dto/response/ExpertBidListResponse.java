package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;

import java.util.List;

public record ExpertBidListResponse(
        List<ExpertBidResponse> expertBidResponseList

) {
    public static ExpertBidListResponse from(List<ExpertBid> expertBidList) {
        return new ExpertBidListResponse(
                expertBidList.stream()
                        .map(ExpertBidResponse::from).toList()
        );
    }
}
