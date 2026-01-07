package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderRequest {

    private Long memberId;
    private List<Long> itemId;
    private List<Integer> count;
}
