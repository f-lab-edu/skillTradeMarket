package com.flab.skilltrademarket.domain.bid.dto.request;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.store.Store;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "고수 응답 요청")
public record ExpertBidCreateRequest(
        @Schema(description = "요청서 Id", example = "1")
        Long userProposalId,
        @Schema(description = "견적 내용", example = "가전 제품 설치 됩니다!")
        String description,
        @Schema(description = "활동 지역", example = "서울시 강남구")
        String activityLocation,
        @Schema(description = "견적 비용", example = "35_000")
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


