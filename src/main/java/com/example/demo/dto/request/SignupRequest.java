package com.example.demo.dto.request;

import com.example.demo.domain.Member;
import lombok.Getter;

@Getter
public class SignupRequest {

    private String name;
    private String street;
    private String zipcode;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .street(this.street)
                .zipcode(this.zipcode)
                .build();
    }
}
