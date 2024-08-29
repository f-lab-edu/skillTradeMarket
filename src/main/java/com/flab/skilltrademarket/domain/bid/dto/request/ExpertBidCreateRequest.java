package com.flab.skilltrademarket.domain.bid.dto.request;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.store.Store;

public record ExpertBidCreateRequest(
        Long userProposalId,
        String description,
        int totalCost

) {
    public static ExpertBid toEntity(
            ExpertBidCreateRequest createRequest,
            UserProposal userProposal,
            Store store) {

        return ExpertBid.builder()
                .userProposal(userProposal)
                .store(store)
                .totalCost(createRequest.totalCost)
                .description(createRequest.description)
                .build();
    }
}


