package com.flab.skilltrademarket.domain.proposal.dto.response;

import com.flab.skilltrademarket.domain.proposal.UserProposal;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "요청서 리스트 응답")
public record UserProposalListResponse(
        List<UserProposalResponse> userProposalResponseList

) {
    public static UserProposalListResponse from(List<UserProposal> userProposalResponseList) {
        return new UserProposalListResponse(
                userProposalResponseList.stream().map(UserProposalResponse::from).toList()
        );
    }

}
