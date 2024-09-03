package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.bid.dto.request.ExpertBidSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CustomExpertBidRepository {

    Slice<ExpertBid> findExpertBidsByAllCondition(ExpertBidSearchCondition searchCondition, Pageable pageable);
}
