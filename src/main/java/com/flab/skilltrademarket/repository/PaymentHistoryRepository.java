package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.pay.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

}
