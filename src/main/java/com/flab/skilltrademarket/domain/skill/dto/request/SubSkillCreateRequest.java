package com.flab.skilltrademarket.domain.skill.dto.request;

import com.flab.skilltrademarket.domain.skill.MainSkill;
import com.flab.skilltrademarket.domain.skill.SubSkill;

public record SubSkillCreateRequest(Long mainSkillId,
                                    String name,
                                    String description) {


    public static SubSkill toEntity(MainSkill mainSkill, SubSkillCreateRequest createRequest) {
        return SubSkill.builder()
                .mainSkill(mainSkill)
                .name(createRequest.name)
                .description(createRequest.description)
                .build();
    }
}
