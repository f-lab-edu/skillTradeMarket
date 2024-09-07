package com.flab.skilltrademarket.domain.proposal.dto.response;

import com.flab.skilltrademarket.domain.proposal.UserProposal;

import java.util.List;

public record UserProposalListResponse(
        List<UserProposalResponse> userProposalResponseList

) {
    public static UserProposalListResponse from(List<UserProposal> userProposalResponseList) {
        return new UserProposalListResponse(
                userProposalResponseList.stream().map(UserProposalResponse::from).toList()
        );
    }

}
