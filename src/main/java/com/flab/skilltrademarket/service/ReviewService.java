package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.reply.Reply;
import com.flab.skilltrademarket.domain.reply.dto.request.ReplyCreateRequest;
import com.flab.skilltrademarket.domain.review.Review;
import com.flab.skilltrademarket.domain.review.dto.request.ReviewCreateRequest;
import com.flab.skilltrademarket.domain.review.dto.response.ReviewListResponse;
import com.flab.skilltrademarket.domain.review.dto.response.ReviewResponse;
import com.flab.skilltrademarket.domain.user.User;
import com.flab.skilltrademarket.global.exception.ApiException;
import com.flab.skilltrademarket.global.exception.ExceptionCode;
import com.flab.skilltrademarket.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final UserRepository userRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final ReplyRepository replyRepository;
    @Transactional
    public void create(Long userId, Long storeId, Long subCategoryId, ReviewCreateRequest request) {
        User writer = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_EXPERT));

        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_SUB_CAT));

        store.calculateRating(request.rating());

        Review review = ReviewCreateRequest.toEntity(request, writer, store, subCategory);
        reviewRepository.save(review);
    }

    public ReviewListResponse findReviewList(Pageable pageable) {
        return ReviewListResponse.from(reviewRepository.findAllByOrderByCreatedAtDesc(pageable));
    }


    public ReviewListResponse findReviewByExpertAndSubCategory(Long storeId, Long subCategoryId, Pageable pageable) {
        Slice<Review> reviews = reviewRepository.findAllByStoreIdAndSubCategoryIdOrderByCreatedAt(storeId, subCategoryId, pageable);
        return ReviewListResponse.from(reviews);
    }

    public ReviewResponse findReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewResponse::from)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_REVIEW));
    }
    @Transactional
    public void createReply(Long reviewId, Long writer, ReplyCreateRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ApiException(ExceptionCode.NOT_FOUND_REVIEW));
        Reply reply = ReplyCreateRequest.toEntity(request, review);

        reply.validateWriter(writer,review);

        replyRepository.save(reply);
    }
}
