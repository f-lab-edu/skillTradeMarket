package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.bid.ExpertBid;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidListResponse;
import com.flab.skilltrademarket.domain.proposal.Status;
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

import java.util.List;

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

        isResponded(userProposal);

        ExpertBid expertBid = ExpertBidCreateRequest.toEntity(createRequest, userProposal, store);
        updateProposalStatus(userProposal, store);

        expertBidRepository.save(expertBid);
    }

    private static void updateProposalStatus(UserProposal userProposal, Store store) {
        Status nextStatus = Status.findNextStatus(userProposal.getStatus());
        userProposal.updateStatus(nextStatus);
        userProposal.updateStore(store);
    }

    private void isResponded(UserProposal userProposal) {
        Long userProposalId = userProposal.getId();

        if(userProposal.getExpertBidList().stream()
                .anyMatch(bid -> isSameBid(bid, userProposalId))){
            throw new ApiException(ExceptionCode.ALREADY_RESPONDED_PROPOSAL);
        }
    }

    private boolean isSameBid(ExpertBid expertBid, Long userProposalId) {
        return expertBid.getUserProposal().getId().equals(userProposalId);
    }

    public ExpertBidListResponse findExpertBids() {
        List<ExpertBid> expertBidList = expertBidRepository.findAllWithFetchJoin();
        return ExpertBidListResponse.from(expertBidList);
    }
}
