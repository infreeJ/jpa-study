package com.example.demo.dto.request;

import lombok.Getter;

@Getter
public class ItemRegistrationRequest {

    private String name;
    private int price;
    private int stockQuantity;
}
