package com.flab.skilltrademarket.domain.bid.dto.request;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.store.Store;

public record ExpertBidCreateRequest(
        Long userProposalId,
        String description,
        String activityLocation,
        Integer totalCost

) {
    public static ExpertBid toEntity(
            ExpertBidCreateRequest createRequest,
            UserProposal userProposal,
            Store store) {

        ExpertBid expertBid = ExpertBid.builder()
                .userProposal(userProposal)
                .store(store)
                .description(createRequest.description)
                .activityLocation(createRequest.activityLocation)
                .totalCost(createRequest.totalCost)
                .build();
        userProposal.addExpertBid(expertBid);
        expertBid.addSubCategory(userProposal.getSubCategory());
        return expertBid;
    }
}


