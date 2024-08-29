package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.bid.dto.request.ExpertBidCreateRequest;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.repository.ExpertBidRepository;
import com.flab.skilltrademarket.repository.StoreRepository;
import com.flab.skilltrademarket.repository.UserProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertBidService {
    private final ExpertBidRepository expertBidRepository;
    private final StoreRepository storeRepository;
    private final UserProposalRepository userProposalRepository;
    public void create(UserDetails user, ExpertBidCreateRequest createRequest) {

        if (!user.role().isExpert()) {
            throw new ApiException(ExceptionCode.NO_ACCESS_EXPERT);
        }

        Store store = storeRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));
        UserProposal userProposal = userProposalRepository.findById(createRequest.userProposalId())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_USER_PROPOSAL));

        ExpertBid expertBid = ExpertBidCreateRequest.toEntity(createRequest, userProposal, store);

        expertBidRepository.save(expertBid);
    }
}
