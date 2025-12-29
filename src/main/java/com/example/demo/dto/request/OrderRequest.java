package com.example.demo.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {

    private Long memberId;
    private List<Long> itemId;
    private List<Integer> count;
}
