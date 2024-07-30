package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.skill.MainSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainSkillRepository extends JpaRepository<MainSkill, Long> {
    boolean existsByName(String name);
}
