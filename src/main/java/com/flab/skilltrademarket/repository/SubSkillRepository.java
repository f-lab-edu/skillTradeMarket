package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.skill.SubSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubSkillRepository extends JpaRepository<SubSkill, Long> {
    boolean existsByName(String name);
}
