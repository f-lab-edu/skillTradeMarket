package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "응답서 리스트 응답")
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
