package com.flab.skilltrademarket.domain.skill.dto.request;

import com.flab.skilltrademarket.domain.skill.MainSkill;

public record MainSkillCreateRequest(String name,
                                     String description) {

    public static MainSkill toEntity(MainSkillCreateRequest createRequest) {
        return MainSkill.builder()
                .name(createRequest.name)
                .description(createRequest.description)
                .build();
    }

}
