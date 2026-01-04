package com.example.demo.dto.response;

import com.example.demo.domain.Item;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    private Long itemId;
    private String name;
    private int price;
    private int stockQuantity;

    public static ItemResponse from(Item item) {
        return ItemResponse.builder()
                .itemId(item.getItemId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .build();
    }
}
