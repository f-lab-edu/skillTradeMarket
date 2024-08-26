package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryListResponse;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.store.dto.request.StoreAddSubCatRequest;
import com.flab.skilltrademarket.domain.store.dto.request.StoreCreateRequest;
import com.flab.skilltrademarket.domain.store.dto.request.StoreUpdateRequest;
import com.flab.skilltrademarket.domain.store.dto.response.StoreResponse;
import com.flab.skilltrademarket.domain.skill.ExpertSkill;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.repository.StoreRepository;
import com.flab.skilltrademarket.repository.ExpertSkillRepository;
import com.flab.skilltrademarket.repository.SubCategoryRepository;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ExpertSkillRepository expertSkillRepository;
    @Transactional
    public void create(UserDetails user, StoreCreateRequest storeCreateRequest) {
        User users = userRepository.findById(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));

        if (!users.getUserRole().isExpert()) {
            throw new ApiException(ExceptionCode.NO_ACCESS_EXPERT);
        }
        checkDupStoreName(storeCreateRequest.storeName());
        Store store = storeCreateRequest.toEntity(users);
        storeRepository.save(store);
    }


    @Transactional
    public void update(UserDetails user, StoreUpdateRequest storeUpdateRequest) {
        Store store = storeRepository.findByUserId(user.id())

                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));
        checkDupStoreName(storeUpdateRequest.storeName());
        Store update = StoreUpdateRequest.update(storeUpdateRequest);

        store.update(update);
    }


    public StoreResponse findById(Long id) {

        return storeRepository.findById(id)
                .map(StoreResponse::from)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));
    }
    @Transactional
    public void delete(UserDetails user) {
        Store store = storeRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        User findUser = userRepository.findById(user.id()).get();
        findUser.changeRole();
        storeRepository.delete(store);
    }



    private void checkDupStoreName(String storeName) {
        if (storeRepository.existsByStoreName(storeName)) {
            throw new ApiException(ExceptionCode.DUP_STORE_NAME);
        }
    }
    @Transactional
    public void addSubCategory(UserDetails user, StoreAddSubCatRequest request) {
        User user1 = userRepository.findById(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        if(!user1.getUserRole().isExpert()){
            throw new ApiException((ExceptionCode.NO_ACCESS_EXPERT));
        }

        Store store = storeRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        SubCategory subCategory = subCategoryRepository.findByName(request.subCatName())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));

        ExpertSkill expertSkill = ExpertSkill.builder()
                .store(store)
                .subCategory(subCategory)
                .build();

        store.addExpertSkill(expertSkill);

        storeRepository.save(store);
    }

    public SubCategoryListResponse getSubCategoryById(Long id) {
        Store store = storeRepository.findByUserId(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        return SubCategoryListResponse.from(store);
    }
    @Transactional
    public void deleteExpertSkill(UserDetails user, StoreAddSubCatRequest request) {
        Store store = storeRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        SubCategory subCategory = subCategoryRepository.findByName(request.subCatName())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));

        ExpertSkill expertSkill = expertSkillRepository.findByStoreAndSubCategory(store, subCategory)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT_SKILL));

        store.deleteExpertSkill(expertSkill);
        expertSkillRepository.delete(expertSkill);
    }
}
