package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.category.SubCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    boolean existsByName(String name);

    Slice<SubCategory> findAllByCategoryName(String name, Pageable pageable);
    @Query(value = "select * from sub_category sc order by sc.REQUEST_COUNT desc limit 10",nativeQuery = true)
    List<SubCategory> findTop10ByRequestCount();
}
