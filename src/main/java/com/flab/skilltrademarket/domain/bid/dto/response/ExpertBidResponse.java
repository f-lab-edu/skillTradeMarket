package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalResponse;
import com.flab.skilltrademarket.domain.store.dto.response.StoreResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "고수 견적 응답")
public record ExpertBidResponse(
        @Schema(description = "스토어 응답")
        StoreResponse store,
        @Schema(description = "요청서 응답")
        UserProposalResponse userProposalResponse,
        @Schema(description = "총 견적 비용",example = "35_000")
        Integer totalCost,
        @Schema(description = "활동 지역",example = "서울시 강남구")
        String activityLocation,
        @Schema(description = "견적 설명",example = "타일 변경")
        String description
) {
    public static ExpertBidResponse from(ExpertBid expertBid) {
        return new ExpertBidResponse(
                StoreResponse.from(expertBid.getStore()),
                UserProposalResponse.from(expertBid.getUserProposal()),
                expertBid.getTotalCost(),
                expertBid.getActivityLocation(),
                expertBid.getDescription());
    }
}
