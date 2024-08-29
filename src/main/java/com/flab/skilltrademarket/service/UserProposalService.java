package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.proposal.dto.request.UserProposalCreateRequest;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalListResponse;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.SubCategoryRepository;
import com.flab.skilltrademarket.repository.UserProposalRepository;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserProposalService {
    private final UserProposalRepository userProposalRepository;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;
    @Transactional
    public void create(Long userId, Long subCategoryId, UserProposalCreateRequest createRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));

        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));

        UserProposal userProposal = UserProposalCreateRequest.toEntity(user, subCategory, createRequest);

        userProposalRepository.save(userProposal);
    }

    public UserProposalListResponse findAllByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        List<UserProposal> allUserProposal = userProposalRepository.findAllByUser(user);
        return new UserProposalListResponse(allUserProposal);
    }
}
