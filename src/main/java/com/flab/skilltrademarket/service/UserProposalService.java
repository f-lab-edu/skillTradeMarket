package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.proposal.dto.request.UserProposalCreateRequest;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalListResponse;
import com.flab.skilltrademarket.domain.proposal.dto.response.UserProposalResponse;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.*;
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
    private final StoreRepository storeRepository;
    private final ExpertSkillRepository expertSkillRepository;
    @Transactional
    public void create(Long userId, Long storeId, UserProposalCreateRequest createRequest) {
        checkExpertSkillExist(storeId, createRequest.subCategoryId());
        UserProposal userProposal = createProposal(userId, createRequest);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));
        userProposal.updateStore(store);
    }

    private void checkExpertSkillExist(Long storeId, Long subCategoryId) {
        if(!expertSkillRepository.existsByStoreIdAndSubCategoryId(storeId, subCategoryId)){
            throw new ApiException(ExceptionCode.NOT_FOUND_EXPERT_SKILL);
        }
    }

    public UserProposal createProposal(Long userId, UserProposalCreateRequest createRequest) {
        User user = findUserById(userId);
        SubCategory subCategory = findSubCategoryById(createRequest.subCategoryId());

        checkUserHasSameSubCategory(user.getId(), subCategory.getId());
        checkAlreadyHasProposal(user.getId(), subCategory.getId());

        UserProposal userProposal = UserProposalCreateRequest.toEntity(user, subCategory, createRequest);
        return userProposalRepository.save(userProposal);
    }

    /**
     * 같은 subCategoryId 로 보낸 요청견적서가 있는지 확인
     * @param userId
     * @param subCategoryId
     */
    private void checkAlreadyHasProposal(Long userId, Long subCategoryId) {
        List<UserProposal> duplicateProposal = userProposalRepository.findByUserIdAndSubCategoryIdAndIsClosedFalse(userId, subCategoryId);
        if (!duplicateProposal.isEmpty()) {
            throw new ApiException(ExceptionCode.DUPLICATE_USER_PROPOSAL);
        }
    }

    /**
     * 요청 보낸 회원이 이미 같은 SubCategory를 가지고 있는지 확인
     * @param userId
     * @param subCategoryId
     */
    private void checkUserHasSameSubCategory(Long userId, Long subCategoryId) {
        Store store = storeRepository.findByUserId(userId).orElse(null);
        if (store != null) {
            if(expertSkillRepository.existsByStoreIdAndSubCategoryId(store.getId(), subCategoryId)){
                throw new ApiException(ExceptionCode.REQUESTER_ALREADY_HAS_SUBCATEGORY);
            }
        }
    }

    public UserProposalResponse findByProposalId(Long userProposalId) {
        return userProposalRepository.findById(userProposalId)
                .map(UserProposalResponse::from)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_USER_PROPOSAL));
    }

    public UserProposalListResponse findAllByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        List<UserProposal> allUserProposal = userProposalRepository.findAllByUser(user);
        return UserProposalListResponse.from(allUserProposal);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
    }

    private SubCategory findSubCategoryById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));
    }

    public void deleteByProposalId(Long userId, Long userProposalId) {
        findUserById(userId);

        UserProposal userProposal = userProposalRepository.findById(userProposalId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_USER_PROPOSAL));
        userProposalRepository.delete(userProposal);
    }
}
