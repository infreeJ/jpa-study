package com.example.demo.dto.request;

import lombok.Getter;

@Getter
public class OrderRequest {

    private Long memberId;
    private Long itemId;
    private Integer count;
}
