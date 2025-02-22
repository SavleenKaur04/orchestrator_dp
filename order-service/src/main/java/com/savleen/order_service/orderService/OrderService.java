package com.savleen.order_service.orderService;

import com.savleen.order_service.Order.Order;
import com.savleen.order_service.OrderDto.OrderRequest;
import com.savleen.order_service.OrderRepository.OrderRepository;
import com.savleen.order_service.orderEvent.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    @Autowired
    private OrderRepository orderRepository;

    public Long createOrder(OrderRequest request) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setUserId(request.getUserId());
        order.setStatus("PENDING");
        orderRepository.save(order);
        kafkaTemplate.send("order_created_topic", new OrderCreatedEvent(order.getId(), order.getProductId(), order.getUserId()));
        return order.getId();
    }
}

