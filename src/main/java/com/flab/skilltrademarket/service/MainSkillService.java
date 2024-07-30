package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.skill.MainSkill;
import com.flab.skilltrademarket.domain.skill.dto.request.MainSkillCreateRequest;
import com.flab.skilltrademarket.domain.skill.dto.request.MainSkillUpdateRequest;
import com.flab.skilltrademarket.domain.skill.dto.response.MainSkillListResponse;
import com.flab.skilltrademarket.domain.skill.dto.response.MainSkillResponse;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.MainSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainSkillService {

    private final MainSkillRepository mainSkillRepository;
    @Transactional
    public void create(MainSkillCreateRequest createRequest) {
        checkDupName(createRequest.name());

        MainSkill mainSkill = MainSkillCreateRequest.toEntity(createRequest);
        mainSkillRepository.save(mainSkill);
    }



    public MainSkillResponse findOneById(Long id) {
        MainSkill mainSkill = mainSkillRepository.findById(id).orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));
        return MainSkillResponse.from(mainSkill);
    }


    public MainSkillListResponse findAll() {
        List<MainSkill> mainSkills = mainSkillRepository.findAll();
        return MainSkillListResponse.from(mainSkills);
    }


    @Transactional
    public MainSkillResponse update(Long id, MainSkillUpdateRequest updateRequest) {
        MainSkill mainSkill = mainSkillRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));

        checkDupName(updateRequest.name());
        MainSkill updateMainSkill = MainSkillUpdateRequest.toEntity(updateRequest);
        mainSkill.update(updateMainSkill);

        return MainSkillResponse.from(mainSkill);
    }

    public void delete(Long id) {
        MainSkill mainSkill = mainSkillRepository.findById(id)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));

        mainSkillRepository.delete(mainSkill);
    }

    private void checkDupName(String name) {
        if (mainSkillRepository.existsByName(name)) {
            throw new ApiException(ExceptionCode.DUP_SKILL_NAME, name);
        }
    }
}
