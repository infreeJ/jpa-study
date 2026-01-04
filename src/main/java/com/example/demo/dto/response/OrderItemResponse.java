package com.example.demo.dto.response;

import com.example.demo.domain.Item;
import com.example.demo.domain.OrderItem;
import lombok.*;

@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
public class OrderItemResponse {

    private Long orderItemId;
    private ItemResponse item;
    private Integer count;
    private Integer orderPrice;

    public static OrderItemResponse from(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .orderItemId(orderItem.getOrderItemId())
                .item(ItemResponse.from(orderItem.getItem()))
                .count(orderItem.getCount())
                .orderPrice(orderItem.getOrderPrice())
                .build();
    }
}
