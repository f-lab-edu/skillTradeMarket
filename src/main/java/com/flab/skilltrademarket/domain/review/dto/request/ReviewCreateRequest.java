package com.flab.skilltrademarket.domain.review.dto.request;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.review.Review;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.utils.ValidationUtil;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "리뷰 생성 요청")
public record ReviewCreateRequest(
        @Schema(description = "내용", example = "서비스 너무 좋았습니다.")
        String content,
        @Schema(description = "평점", example = "4")
        Integer rating
) {

    public static Review toEntity(ReviewCreateRequest request, User writer, Store store, SubCategory subCategory) {
        request.validCheck();
        return Review.builder()
                .writer(writer)
                .store(store)
                .subCategory(subCategory)
                .content(request.content)
                .rating(request.rating())
                .build();
    }


    private void validCheck() {
        ValidationUtil.validateRating(rating);
    }
}
