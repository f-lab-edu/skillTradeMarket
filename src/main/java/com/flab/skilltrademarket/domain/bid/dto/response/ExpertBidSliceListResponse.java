package com.flab.skilltrademarket.domain.bid.dto.response;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Slice;

import java.util.List;
@Schema(description = "응답서 페이징 리스트 응답")
public record ExpertBidSliceListResponse(
        List<ExpertBidSliceResponse> expertBidSliceResponses,
        @Schema(description = "페이징 존재여부")
        boolean hasNext
) {
    public static ExpertBidSliceListResponse from(Slice<ExpertBid> expertBids) {
        List<ExpertBidSliceResponse> sliceResponseList = expertBids.stream()
                .map(ExpertBidSliceResponse::from)
                .toList();
        return new ExpertBidSliceListResponse(sliceResponseList, expertBids.hasNext());
    }
}
