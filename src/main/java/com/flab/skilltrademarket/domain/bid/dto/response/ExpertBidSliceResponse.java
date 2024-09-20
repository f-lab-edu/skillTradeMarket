package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.Status;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "응답서 페이징 응답")
public record ExpertBidSliceResponse(
        @Schema(description = "응답서 id",example = "1")
        Long id,
        @Schema(description = "응답서 응답")
        ExpertBidResponse expertBidResponse,
        @Schema(description = "진행 상태", example = "PROCEEDING")
        Status status

) {
    public static ExpertBidSliceResponse from(ExpertBid expertBid) {
        return new ExpertBidSliceResponse(
                expertBid.getId(),
                ExpertBidResponse.from(expertBid),
                expertBid.getUserProposal().getStatus()
        );
    }
}
