package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidDto;

import java.util.List;

public interface CustomExpertBidRepository {

    List<ExpertBidDto> findAllByExpertBidIdPage(Long expertBidId, Long cursorId);
}
