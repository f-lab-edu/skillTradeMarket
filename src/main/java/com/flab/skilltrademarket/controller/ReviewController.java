package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.reply.dto.request.ReplyCreateRequest;
import com.flab.skilltrademarket.domain.review.dto.request.ReviewCreateRequest;
import com.flab.skilltrademarket.domain.review.dto.response.ReviewListResponse;
import com.flab.skilltrademarket.domain.review.dto.response.ReviewResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 생성
     * @param user
     * @param expertId
     * @param subCategoryId
     * @param request
     */
    @PostMapping("/review/{expertId}")
    public void create(@AuthenticationUser UserDetails user, @PathVariable("expertId") Long expertId, @RequestParam("subCategoryId") Long subCategoryId, @RequestBody ReviewCreateRequest request) {
        reviewService.create(user.id(), expertId, subCategoryId, request);
    }

    /**
     * 리뷰 와 답글 전체 조회
     * @param pageable
     * @return
     */
    @GetMapping("/review")
    public CommonResponse<ReviewListResponse> findReviewList(
            @PageableDefault(sort = "updatedAt", size = 10, direction = Sort.Direction.DESC)
            Pageable pageable){
        return CommonResponse.success(reviewService.findReviewList(pageable));
    }

    /**
     * 특정 고수에 대한 리뷰 답글 전체 조회
     * @param expertId
     * @param subCategoryId
     * @param pageable
     * @return
     */
    @GetMapping("/review/expert")
    public CommonResponse<ReviewListResponse> findReviewByExpert(@RequestParam("expertId") Long expertId,
                                                                 @RequestParam("subCategoryId") Long subCategoryId,
                                                                 @PageableDefault(sort = "updatedAt", size = 10, direction = Sort.Direction.DESC)
                                                                     Pageable pageable) {
        return CommonResponse.success(reviewService.findReviewByExpertAndSubCategory(expertId, subCategoryId, pageable));
    }

    /**
     * 리뷰 아이디를 통한 단건 조회
     * @param id
     * @return
     */
    @GetMapping("/review/{id}")
    public CommonResponse<ReviewResponse> findReviewById(@PathVariable("id") Long id) {
        return CommonResponse.success(reviewService.findReviewById(id));
    }

    /**
     * 답글 작성
     * @param reviewId
     * @param user
     * @param request
     */
    @PostMapping("/review/{reviewId}/reply")
    public void createReply(@PathVariable("reviewId") Long reviewId, @AuthenticationUser UserDetails user, @RequestBody ReplyCreateRequest request) {
        reviewService.createReply(reviewId, user.id(), request);
    }

}
