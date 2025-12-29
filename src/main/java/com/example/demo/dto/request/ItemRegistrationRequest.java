package com.example.demo.dto.request;

import com.example.demo.domain.Item;
import lombok.Getter;

@Getter
public class ItemRegistrationRequest {

    private String name;
    private int price;
    private int stockQuantity;

    public Item toEntity() {
        return Item.builder()
                .name(this.name)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }
}
