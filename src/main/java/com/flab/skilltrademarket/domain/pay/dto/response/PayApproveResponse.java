package com.flab.skilltrademarket.domain.pay.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
public record PayApproveResponse(
        String aid,
        String tid,
        String partner_order_id,
        String partner_user_id,
        String payment_method_type,
        Amount amount,
        String item_name,
        String item_code,
        String created_at,
        String approved_at,
        String payload

) {
    @Getter
    public static class Amount{
        private Integer total;
        private Integer tax_free;
        private Integer tax;
    }
}
