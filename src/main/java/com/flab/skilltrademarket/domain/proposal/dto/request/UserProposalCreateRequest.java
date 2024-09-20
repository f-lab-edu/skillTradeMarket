package com.flab.skilltrademarket.domain.proposal.dto.request;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "요청서 생성 요청")
public record UserProposalCreateRequest(
        @Schema(description = "하위 카테고리 Id", example = "1")
        Long subCategoryId,
        @Schema(description = "위치", example = "서울시 강남구")
        String location,
        @Schema(description = "상세 설명", example = "냉동고가 고장이 난거 같아요")
        String detailedDescription,
        @Schema(description = "시작 시간", example = "2024-09-30T13:11:20")
        LocalDateTime startDate
) {
    public static UserProposal toEntity(User user, SubCategory subCategory,
                                        UserProposalCreateRequest createRequest) {
        return UserProposal.builder()
                .user(user)
                .subCategory(subCategory)
                .location(createRequest.location)
                .detailedDescription(createRequest.detailedDescription)
                .strDate(createRequest.startDate)
                .build();
    }
}
