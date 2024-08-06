package com.flab.skilltrademarket.domain;

import com.flab.skilltrademarket.domain.expert.dto.response.PopularExpertResponse;
import com.flab.skilltrademarket.domain.skill.dto.response.PopularSkillResponse;

import java.util.List;

public record HomeResponse(
        List<PopularSkillResponse> topTenSkill,
        List<PopularExpertResponse> topTwoExpert
) {
    public static HomeResponse from(List<PopularSkillResponse> topTenSkill, List<PopularExpertResponse> topTwoExpert) {
        return new HomeResponse(topTenSkill, topTwoExpert);
    }
}
