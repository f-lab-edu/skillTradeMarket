package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.Status;

public record ExpertBidSliceResponse(
        Long id,
        ExpertBidResponse expertBidResponse,
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
