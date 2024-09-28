package com.flab.skilltrademarket.domain.pay.dto.request;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.pay.PaymentHistory;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "결제 정보 요청")
public record PayInfoRequest(
        @Schema(description = "요청서 Id", example = "1")
        Long proposalId,
        @Schema(description = "요청 상품명", example = "가전제품 설치")
        String itemName,
        @Schema(description = "가격", example = "35000")
        Integer totalCost
) {
    public static PaymentHistory toEntity(UserProposal userProposal, ExpertBid bid) {
        return PaymentHistory.builder()
                .userProposalId(userProposal.getId())
                .expertBidId(bid.getId())
                .itemName(userProposal.getDetailedDescription())
                .totalCost(bid.getTotalCost())
                .build();
    }
}
