package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByUserId(Long userId);

    boolean existsByStoreName(String storeName);
}
