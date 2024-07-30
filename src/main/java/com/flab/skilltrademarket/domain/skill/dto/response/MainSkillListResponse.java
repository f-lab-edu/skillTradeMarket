package com.flab.skilltrademarket.domain.skill.dto.response;

import com.flab.skilltrademarket.domain.skill.MainSkill;

import java.util.List;

public record MainSkillListResponse(List<MainSkillResponse> skillList) {

    public static MainSkillListResponse from(List<MainSkill> mainSkillList) {
        return new MainSkillListResponse(mainSkillList.stream()
                .map(MainSkillResponse::from)
                .toList());
    }
}
