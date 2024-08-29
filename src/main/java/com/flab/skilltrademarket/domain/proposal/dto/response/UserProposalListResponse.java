package com.flab.skilltrademarket.domain.proposal.dto.response;

import com.flab.skilltrademarket.domain.proposal.UserProposal;

import java.util.List;

public record UserProposalListResponse(
        List<UserProposal> userProposalResponseList

) {
}
