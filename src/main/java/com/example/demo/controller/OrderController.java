package com.example.demo.controller;

import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void order(@RequestBody OrderRequest orderRequest) {
        orderService.order(orderRequest);
    }

    @DeleteMapping("/cancel")
    public void cancel(@RequestParam Long orderId) {
        orderService.cancel(orderId);
    }

    @GetMapping("/orders/{memberId}")
    public List<OrderResponse> findMemberOrders(@PathVariable Long memberId) {
        return orderService.findMemberOrders(memberId);
    }

}
