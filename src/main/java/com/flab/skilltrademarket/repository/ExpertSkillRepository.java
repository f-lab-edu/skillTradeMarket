package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.category.SubCategory;
import com.flab.skilltrademarket.domain.expert.Expert;
import com.flab.skilltrademarket.domain.skill.ExpertSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertSkillRepository extends JpaRepository<ExpertSkill, Long> {

    Optional<ExpertSkill> findByExpertAndSubCategory(Expert expert, SubCategory subCategory);
}
