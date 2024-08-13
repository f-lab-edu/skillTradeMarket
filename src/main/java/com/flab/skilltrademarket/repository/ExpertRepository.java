package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.expert.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long> {

    Optional<Expert> findByUserId(Long userId);

    boolean existsByStoreName(String storeName);
}
