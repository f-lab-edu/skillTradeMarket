package com.flab.skilltrademarket.domain.proposal.dto.response;

import com.flab.skilltrademarket.domain.proposal.UserProposal;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "요청서 응답")
public record UserProposalResponse(
        @Schema(description = "요청서 Id",example = "1")
        Long id,
        @Schema(description = "요청한 사용자 Id",example = "1")
        Long userId,
        @Schema(description = "하위 스킬 Id",example = "1")
        Long subCategoryId,
        @Schema(description = "선호 위치",example = "서울시 강남구")
        String location,
        @Schema(description = "상세 요청 사항",example = "화장실 타일 변경", nullable = true)
        String detailedDescription,
        @Schema(description = "선호 시작일",example = "2024-09-30T13:11:20",nullable = true)
        LocalDateTime startDate
) {
    public static UserProposalResponse from(UserProposal userProposal) {
        return new UserProposalResponse(userProposal.getId(),
                userProposal.getUser().getId(),
                userProposal.getSubCategory().getId(),
                userProposal.getLocation(),
                userProposal.getDetailedDescription(),
                userProposal.getPreferredStartDate());
    }
}

