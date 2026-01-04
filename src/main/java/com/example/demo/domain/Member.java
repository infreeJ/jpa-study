package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column
    private String street;

    @Column
    private String zipcode;
}
