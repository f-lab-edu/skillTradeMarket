package com.flab.skilltrademarket.domain.skill.dto.response;

import com.flab.skilltrademarket.domain.skill.MainSkill;

public record MainSkillResponse(Long id,
                                String name,
                                String description) {

    public static MainSkillResponse from(MainSkill mainSkill) {
        return new MainSkillResponse(mainSkill.getId(), mainSkill.getName(), mainSkill.getDescription());
    }
}
