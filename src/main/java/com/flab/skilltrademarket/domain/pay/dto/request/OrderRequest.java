package com.flab.skilltrademarket.domain.pay.dto.request;

import com.flab.skilltrademarket.domain.pay.Order;
import com.flab.skilltrademarket.domain.proposal.UserProposal;

public record OrderRequest(
        Long userProposalId,
        String productName,
        Integer price,
        String impUid,
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
