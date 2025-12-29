package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private int stockQuantity;
}
