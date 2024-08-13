package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.category.dto.response.SubCategoryListResponse;
import com.flab.skilltrademarket.domain.expert.Expert;
import com.flab.skilltrademarket.domain.expert.dto.request.ExpertAddSubCatRequest;
import com.flab.skilltrademarket.domain.expert.dto.request.ExpertCreateRequest;
import com.flab.skilltrademarket.domain.expert.dto.request.ExpertUpdateRequest;
import com.flab.skilltrademarket.domain.expert.dto.response.ExpertResponse;
import com.flab.skilltrademarket.domain.skill.ExpertSkill;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.domain.user.UserRole;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.repository.ExpertRepository;
import com.flab.skilltrademarket.repository.ExpertSkillRepository;
import com.flab.skilltrademarket.repository.SubCategoryRepository;
import com.flab.skilltrademarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExpertService {

    private final ExpertRepository expertRepository;
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ExpertSkillRepository expertSkillRepository;
    @Transactional
    public void create(UserDetails user, ExpertCreateRequest expertCreateRequest) {
        User userId = userRepository.findById(user.id()).orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));
        if(user.role().equals(UserRole.EXPERT)){
            checkDupStoreName(expertCreateRequest.storeName());
            Expert expert = expertCreateRequest.toEntity(userId);
            expertRepository.save(expert);
        }else{
            throw new ApiException(ExceptionCode.NO_ACCESS_EXPERT);
        }
    }


    @Transactional
    public void update(UserDetails user, ExpertUpdateRequest expertUpdateRequest) {
        Expert expert = expertRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));
        checkDupStoreName(expertUpdateRequest.storeName());
        Expert update = ExpertUpdateRequest.update(expertUpdateRequest);

        expert.update(update);
    }


    public ExpertResponse findById(Long id) {

        Expert expert = expertRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));
        return ExpertResponse.from(expert);
    }
    @Transactional
    public void delete(UserDetails user) {
        Expert expert = expertRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        User findUser = userRepository.findById(user.id()).get();
        findUser.changeRole();
        expertRepository.delete(expert);
    }



    private void checkDupStoreName(String storeName) {
        if (expertRepository.existsByStoreName(storeName)) {
            throw new ApiException(ExceptionCode.DUP_STORE_NAME);
        }
    }
    @Transactional
    public void addSubCategory(UserDetails user, ExpertAddSubCatRequest request) {
        Expert expert = expertRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        SubCategory subCategory = subCategoryRepository.findByName(request.subCatName())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));

        ExpertSkill expertSkill = ExpertSkill.builder()
                .expert(expert)
                .subCategory(subCategory)
                .build();

        expert.addExpertSkill(expertSkill);

        expertRepository.save(expert);
    }

    public SubCategoryListResponse getSubCategoryById(Long id) {
        Expert expert = expertRepository.findByUserId(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        return SubCategoryListResponse.from(expert);
    }
    @Transactional
    public void deleteExpertSkill(UserDetails user, ExpertAddSubCatRequest request) {
        Expert expert = expertRepository.findByUserId(user.id())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        SubCategory subCategory = subCategoryRepository.findByName(request.subCatName())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));

        ExpertSkill expertSkill = expertSkillRepository.findByExpertAndSubCategory(expert, subCategory)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT_SKILL));

        expert.deleteExpertSkill(expertSkill);
        expertSkillRepository.delete(expertSkill);
    }
}
