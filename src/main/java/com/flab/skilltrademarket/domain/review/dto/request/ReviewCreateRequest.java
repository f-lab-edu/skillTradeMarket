package com.flab.skilltrademarket.domain.review.dto.request;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.review.Review;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.utils.ValidationUtil;

public record ReviewCreateRequest(
    String content,
    int rating
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
