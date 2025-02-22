package com.savleen.order_service.OrderRepository;

import com.savleen.order_service.Order.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
