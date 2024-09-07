package com.flab.skilltrademarket.domain.proposal.dto.response;

import com.flab.skilltrademarket.domain.proposal.UserProposal;

import java.time.LocalDateTime;

public record UserProposalResponse(
        Long id,
        Long userId,
        Long subCategoryId,
        String location,
        String detailedDescription,
        LocalDateTime startDate
) {
    public static UserProposalResponse from(UserProposal userProposal) {
        return new UserProposalResponse(userProposal.getId(),
                userProposal.getUser().getId(),
                userProposal.getSubCategory().getId(),
                userProposal.getLocation(),
                userProposal.getDetailedDescription(),
                userProposal.getPreferredStartDate());
    }
}

