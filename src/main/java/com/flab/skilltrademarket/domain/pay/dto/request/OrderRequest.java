package com.flab.skilltrademarket.domain.pay.dto.request;

import com.flab.skilltrademarket.domain.pay.Order;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주문 요청")
public record OrderRequest(
        @Schema(description = "요청서 Id", example = "1")
        Long userProposalId,
        @Schema(description = "요청 서비스 명", example = "가전 제품")
        String productName,
        @Schema(description = "가격", example = "35_000")
        Integer price,
        @Schema(description = "결제정보고유Id", example = "imp01231391")
        String impUid,
        @Schema(description = "상점고유Id", example = "stm020101123")
        String merchantUid
) {
    public Order toEntity(UserProposal userProposal) {
        return Order.builder()
                .userProposal(userProposal)
                .productName(productName)
                .price(price)
                .impUid(impUid)
                .merchantUid(merchantUid)
                .build();
    }
}
