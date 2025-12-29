package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Column
    private String name;

    @Column
    private String street;

    @Column
    private String zipcode;
}
