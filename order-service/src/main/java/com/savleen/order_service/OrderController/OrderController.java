package com.savleen.order_service.OrderController;

import com.savleen.order_service.OrderDto.OrderRequest;
import com.savleen.order_service.orderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        Long orderId = orderService.createOrder(request);
        return ResponseEntity.ok("Order Created: " + orderId);
    }
}

