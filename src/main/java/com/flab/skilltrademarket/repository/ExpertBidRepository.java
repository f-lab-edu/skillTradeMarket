package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpertBidRepository extends JpaRepository<ExpertBid,Long>, CustomExpertBidRepository{
    @Query("SELECT DISTINCT e FROM ExpertBid e JOIN FETCH e.store JOIN FETCH e.userProposal")
    List<ExpertBid> findAllWithFetchJoin();

    Optional<ExpertBid> findByUserProposalId(Long userProposalId);
}
