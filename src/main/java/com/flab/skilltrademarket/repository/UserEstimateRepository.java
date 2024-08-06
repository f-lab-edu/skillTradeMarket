package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.estimate.UserEstimate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserEstimateRepository extends JpaRepository<UserEstimate,Long> {
}
