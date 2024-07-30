package com.flab.skilltrademarket.domain.skill.dto.request;

import com.flab.skilltrademarket.domain.skill.MainSkill;

public record MainSkillUpdateRequest(String name,
                                     String description) {

    public static MainSkill toEntity(MainSkillUpdateRequest updateRequest) {
        return MainSkill.builder()
                .name(updateRequest.name)
                .description(updateRequest.description)
                .build();
    }
}
