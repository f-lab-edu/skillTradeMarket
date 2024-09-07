package com.flab.skilltrademarket.domain.proposal.dto.request;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.user.User;

import java.time.LocalDateTime;

public record UserProposalCreateRequest(
        Long subCategoryId,
        String location,
        String detailedDescription,
        LocalDateTime startDate
) {
    public static UserProposal toEntity(User user, SubCategory subCategory,
                                        UserProposalCreateRequest createRequest) {
        return UserProposal.builder()
                .user(user)
                .subCategory(subCategory)
                .location(createRequest.location)
                .detailedDescription(createRequest.detailedDescription)
                .strDate(createRequest.startDate)
                .build();
    }
}
