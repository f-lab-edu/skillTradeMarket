package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.estimate.UserEstimate;
import com.flab.skilltrademarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserEstimateRepository extends JpaRepository<UserEstimate,Long> {
    List<UserEstimate> findAllByUser(User user);
}
