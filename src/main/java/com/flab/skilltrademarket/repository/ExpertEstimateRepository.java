package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.estimate.ExpertEstimate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertEstimateRepository extends JpaRepository<ExpertEstimate,Long> {
}
