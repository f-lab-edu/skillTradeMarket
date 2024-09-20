package com.flab.skilltrademarket.domain;

import com.flab.skilltrademarket.domain.store.dto.response.PopularStoreResponse;
import com.flab.skilltrademarket.domain.skill.dto.response.PopularSkillResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "홈 응답")
public record HomeResponse(
        @Schema(description = "인기 서비스 응답 리스트")
        List<PopularSkillResponse> topTenSkill,
        @Schema(description = "인기 스토어 응답 리스트")
        List<PopularStoreResponse> topTwoExpert
) {
    public static HomeResponse from(List<PopularSkillResponse> topTenSkill, List<PopularStoreResponse> topTwoExpert) {
        return new HomeResponse(topTenSkill, topTwoExpert);
    }
}
