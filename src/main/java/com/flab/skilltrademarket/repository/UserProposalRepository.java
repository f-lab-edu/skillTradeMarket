package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.proposal.UserProposal;
import com.flab.skilltrademarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserProposalRepository extends JpaRepository<UserProposal,Long> {
    List<UserProposal> findAllByUser(User user);


    List<UserProposal> findByUserIdAndSubCategoryIdAndIsClosedFalse(Long userId, Long subCategoryId);
}
