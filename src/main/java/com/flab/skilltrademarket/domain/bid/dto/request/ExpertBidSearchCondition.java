package com.flab.skilltrademarket.domain.bid.dto.request;

import com.flab.skilltrademarket.domain.proposal.Status;

public record ExpertBidSearchCondition(
         Long userId,
         Status status,
         Long expertBidId
) {

}
