package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r.expert.storeName,r.subCategory.name,r.rating,r.content,r.writer.nickname from Review r order by r.rating desc")
    List<Review> findTop2ByRating();
}
