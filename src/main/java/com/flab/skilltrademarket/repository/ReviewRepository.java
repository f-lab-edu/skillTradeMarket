package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r.expert.storeName,r.subCategory.name,r.rating,r.content,r.writer.nickname from Review r order by r.rating desc")
    List<Review> findTop2ByRating();

    Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("select r from Review r where r.expert.id = ?1 and (?2 IS NULL or r.subCategory.id = ?2) order by r.createdAt")
    Slice<Review> findAllByExpertIdAndSubCategoryIdOrderByCreatedAt(@Param("expertId")Long expertId, @Param("subCategoryId") Long subCategoryId, Pageable pageable);
}
