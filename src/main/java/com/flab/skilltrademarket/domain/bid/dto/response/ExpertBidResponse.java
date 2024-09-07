package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalResponse;
import com.flab.skilltrademarket.domain.store.dto.response.StoreResponse;

public record ExpertBidResponse(
        StoreResponse store,
        UserProposalResponse userProposalResponse,
        int totalCost,
        String activityLocation,
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
