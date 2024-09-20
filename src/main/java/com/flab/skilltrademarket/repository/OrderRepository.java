package com.flab.skilltrademarket.repository;

import com.flab.skilltrademarket.domain.pay.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
