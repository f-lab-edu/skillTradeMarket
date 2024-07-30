package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.skill.MainSkill;
import com.flab.skilltrademarket.domain.skill.SubSkill;
import com.flab.skilltrademarket.domain.skill.dto.request.SubSkillCreateRequest;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.MainSkillRepository;
import com.flab.skilltrademarket.repository.SubSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubSkillService {

    private final SubSkillRepository subSkillRepository;
    private final MainSkillRepository mainSkillRepository;
    @Transactional
    public void create(SubSkillCreateRequest createRequest) {
        MainSkill mainSkill = mainSkillRepository.findById(createRequest.mainSkillId())
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SKILL));

        dupCheckName(createRequest.name());

        SubSkill subSkill = SubSkillCreateRequest.toEntity(mainSkill, createRequest);

        subSkillRepository.save(subSkill);
    }

    private void dupCheckName(String name) {
        if(subSkillRepository.existsByName(name)){
            throw new ApiException(ExceptionCode.DUP_SKILL_NAME,name);
        }
    }
}
