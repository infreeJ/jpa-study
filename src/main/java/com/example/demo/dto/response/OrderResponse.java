package com.example.demo.dto.response;

import com.example.demo.domain.Item;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long orderId;
    private List<OrderItemResponse> orderItems;
    private int totalPrice;
    private Status status;

    public static OrderResponse from(Order order) {

        List<OrderItemResponse> orderItemResponseList = new ArrayList<>();
        for(OrderItem orderItem : order.getOrderItems()) {

            OrderItemResponse orderItemResponse = OrderItemResponse.from(orderItem);
            orderItemResponseList.add(orderItemResponse);
        }

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderItems(orderItemResponseList)
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }
}
