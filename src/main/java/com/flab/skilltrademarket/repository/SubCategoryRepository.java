package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    boolean existsByName(String name);
}
