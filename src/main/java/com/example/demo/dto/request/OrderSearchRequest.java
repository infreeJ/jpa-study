package com.example.demo.dto.request;

import com.example.demo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchRequest {
    private String name;
    private Status status;
}
