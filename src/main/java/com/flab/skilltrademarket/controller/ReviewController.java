package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.bid.dto.response.ExpertBidListResponse;
import com.flab.skilltrademarket.domain.reply.dto.request.ReplyCreateRequest;
import com.flab.skilltrademarket.domain.review.dto.request.ReviewCreateRequest;
import com.flab.skilltrademarket.domain.review.dto.response.ReviewListResponse;
import com.flab.skilltrademarket.domain.review.dto.response.ReviewResponse;
import com.flab.skilltrademarket.global.security.model.UserDetails;
import com.flab.skilltrademarket.global.security.resolver.AuthenticationUser;
import com.flab.skilltrademarket.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰", description = "리뷰 생성,리뷰와 답글 전체 조회, 특정 고수에 대한 리뷰 답글 전체 조회, 리뷰 아이디를 통한 단건 조회, 답글 작성 API")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 생성
     *
     * @param user
     * @param storeId
     * @param subCategoryId
     * @param request
     */
    @PostMapping("/stm/review/{expertId}")
    @Operation(
            summary = "리뷰 생성",
            description = "리뷰를 생성합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "리뷰 생성에 성공하였습니다.")
            }
    )
    public void create(@AuthenticationUser UserDetails user, @Parameter(description = "스토어 Id", example = "1") @PathVariable("storeId") Long storeId,
                       @Parameter(description = "하위 카테고리 Id", example = "1") @RequestParam("subCategoryId") Long subCategoryId,
                       @RequestBody ReviewCreateRequest request) {
        reviewService.create(user.id(), storeId, subCategoryId, request);
    }

    /**
     * 리뷰 와 답글 전체 조회
     *
     * @param pageable
     * @return
     */
    @GetMapping("/stm/review")
    @Operation(
            summary = "리뷰와 답글 전체 조회",
            description = "리뷰와 답글 전체 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "리뷰와 답글 전체 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = ReviewListResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<ReviewListResponse> findReviewList(
            @PageableDefault(sort = "updatedAt", size = 10, direction = Sort.Direction.DESC)
            Pageable pageable) {
        return CommonResponse.success(reviewService.findReviewList(pageable));
    }

    /**
     * 특정 고수에 대한 리뷰 답글 전체 조회
     *
     * @param storeId
     * @param subCategoryId
     * @param pageable
     * @return
     */
    @GetMapping("/stm/review/expert")
    @Operation(
            summary = "특정 고수에 대한 리뷰 답글 전체 조회",
            description = "특정 고수에 대한 리뷰 답글 전체 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "특정 고수에 대한 리뷰 답글 전체 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = ReviewListResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<ReviewListResponse> findReviewByExpert(@Parameter(description = "스토어 Id", example = "1")@RequestParam("storeId") Long storeId,
                                                                 @Parameter(description = "하위카테고리 Id",example = "1")@RequestParam("subCategoryId") Long subCategoryId,
                                                                 @PageableDefault(sort = "updatedAt", size = 10, direction = Sort.Direction.DESC)
                                                                 Pageable pageable) {
        return CommonResponse.success(reviewService.findReviewByExpertAndSubCategory(storeId, subCategoryId, pageable));
    }

    /**
     * 리뷰 아이디를 통한 단건 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/stm/review/{id}")
    @Operation(
            summary = "리뷰 단건 조회",
            description = "리뷰 단건 조회합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "리뷰 단건 조회에 성공하였습니다.",content = @Content(schema = @Schema(implementation = ReviewResponse.class))),
                @ApiResponse(responseCode = "404", description = "Bad Request",content = @Content(schema = @Schema(implementation = CommonResponse.class)))
            }
    )
    public CommonResponse<ReviewResponse> findReviewById(@Parameter(description = "리뷰 Id", example = "1")@PathVariable("id") Long id) {
        return CommonResponse.success(reviewService.findReviewById(id));
    }

    /**
     * 답글 작성
     *
     * @param reviewId
     * @param user
     * @param request
     */
    @PostMapping("/stm/review/{reviewId}/reply")
    @Operation(
            summary = "답글 생성",
            description = "답글을 생성합니다.",
            responses = {
                @ApiResponse(responseCode = "200", description = "답글 생성에 성공하였습니다.")
            }
    )
    public void createReply(@PathVariable("reviewId") Long reviewId, @AuthenticationUser UserDetails user, @RequestBody ReplyCreateRequest request) {
        reviewService.createReply(reviewId, user.id(), request);
    }

}
