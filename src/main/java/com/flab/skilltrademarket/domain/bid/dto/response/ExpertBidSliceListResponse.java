package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import org.springframework.data.domain.Slice;

import java.util.List;

public record ExpertBidSliceListResponse(
        List<ExpertBidSliceResponse> expertBidSliceResponses,
        boolean hasNext
) {
    public static ExpertBidSliceListResponse from(Slice<ExpertBid> expertBids) {
        List<ExpertBidSliceResponse> sliceResponseList = expertBids.stream()
                .map(ExpertBidSliceResponse::from)
                .toList();
        return new ExpertBidSliceListResponse(sliceResponseList, expertBids.hasNext());
    }
}
