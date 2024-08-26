package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.store.Store;
import com.flab.skilltrademarket.domain.skill.ExpertSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertSkillRepository extends JpaRepository<ExpertSkill, Long> {

    Optional<ExpertSkill> findByStoreAndSubCategory(Store store, SubCategory subCategory);
}
