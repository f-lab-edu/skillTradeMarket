package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertBidRepository extends JpaRepository<ExpertBid,Long> {
    @Query("SELECT DISTINCT e FROM ExpertBid e JOIN FETCH e.store JOIN FETCH e.userProposal")
    List<ExpertBid> findAllWithFetchJoin();
}
