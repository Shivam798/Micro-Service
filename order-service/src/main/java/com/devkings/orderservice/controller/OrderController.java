package com.devkings.orderservice.controller;

import com.devkings.orderservice.dto.OrderRequest;
import com.devkings.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order place successfully";
    }

    @GetMapping("/name")
    public String dump(){
        return "Hello";
    }
}
