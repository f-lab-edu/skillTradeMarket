package com.flab.skilltrademarket.domain.bid.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record ExpertBidDto(
        Long expertBidId,
        Long storeId,
        String storeName,
        int maxDistance,
        String location,
        double rating,
        Long userProposalId,
        Long userId,
        Long subCategoryId,
        Integer totalCost,
        String activityLocation,
        String description,
        String detailedDescription,
        LocalDateTime startDate

) {
    @QueryProjection
    public ExpertBidDto(Long expertBidId, Long storeId, String storeName, int maxDistance, String location, double rating, Long userProposalId, Long userId, Long subCategoryId, Integer totalCost, String activityLocation, String description, String detailedDescription, LocalDateTime startDate) {
        this.expertBidId = expertBidId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.maxDistance = maxDistance;
        this.location = location;
        this.rating = rating;
        this.userProposalId = userProposalId;
        this.userId = userId;
        this.subCategoryId = subCategoryId;
        this.totalCost = totalCost;
        this.activityLocation = activityLocation;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.startDate = startDate;
    }
}
