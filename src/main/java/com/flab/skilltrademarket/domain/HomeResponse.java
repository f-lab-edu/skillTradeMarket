package com.flab.skilltrademarket.domain;

import com.flab.skilltrademarket.domain.store.dto.response.PopularStoreResponse;
import com.flab.skilltrademarket.domain.skill.dto.response.PopularSkillResponse;

import java.util.List;

public record HomeResponse(
        List<PopularSkillResponse> topTenSkill,
        List<PopularStoreResponse> topTwoExpert
) {
    public static HomeResponse from(List<PopularSkillResponse> topTenSkill, List<PopularStoreResponse> topTwoExpert) {
        return new HomeResponse(topTenSkill, topTwoExpert);
    }
}
