package com.flab.skilltrademarket.service;

import com.flab.skilltrademarket.domain.HomeResponse;
import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.store.dto.response.PopularStoreResponse;
import com.flab.skilltrademarket.domain.review.Review;
import com.flab.skilltrademarket.domain.skill.dto.response.PopularSkillResponse;
import com.flab.skilltrademarket.repository.ReviewRepository;
import com.flab.skilltrademarket.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final SubCategoryRepository subCategoryRepository;
    private final ReviewRepository reviewRepository;
    @Transactional(readOnly = true)
    public HomeResponse getHome() {
        List<PopularSkillResponse> topTenSkill = getTopTenSkill(subCategoryRepository.findTop10ByRequestCount());
        List<PopularStoreResponse> topTwoExpert = getTopTwoExpert(reviewRepository.findTop2ByRating());
        return new HomeResponse(topTenSkill, topTwoExpert);
    }

    private List<PopularSkillResponse> getTopTenSkill(List<SubCategory> categories) {
        return categories.stream()
                .map(sub -> PopularSkillResponse.fromEntity(sub))
                .collect(Collectors.toList());
    }

    private List<PopularStoreResponse> getTopTwoExpert(List<Review> reviews) {
        return reviews.stream()
                .map(review -> PopularStoreResponse.fromEntity(review))
                .collect(Collectors.toList());
    }
}
