package com.flab.skilltrademarket.domain.bid.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ExpertBidPageResponse {
    private final List<ExpertBidResponse> expertBidResponses;
    private final Long cursorId;
    private final boolean hasNext;

    public ExpertBidPageResponse() {
        this.expertBidResponses = new ArrayList<>();
        this.cursorId = -1L;
        this.hasNext = false;
    }

    public ExpertBidPageResponse(List<ExpertBidDto> expertBidDtos, Long cursorId) {
        this.expertBidResponses = toExpertBidResponseList(expertBidDtos).subList(0, Math.min(10, expertBidDtos.size()));
        this.cursorId = cursorId;
        this.hasNext = expertBidDtos.size() >= 10;
    }

    public ExpertBidPageResponse(List<ExpertBidDto> expertBidDtos) {
        this.expertBidResponses = toExpertBidResponseList(expertBidDtos);
        this.cursorId = -1L;
        this.hasNext = false;
    }

    private List<ExpertBidResponse> toExpertBidResponseList(List<ExpertBidDto> expertBidDtos) {
        return expertBidDtos.stream()
                .map(ExpertBidResponse::toExpertBidResponse)
                .collect(Collectors.toList());
    }
}
