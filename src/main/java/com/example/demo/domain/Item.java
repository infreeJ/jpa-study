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
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Version
    private Long version;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private int stockQuantity;

    public void decrease(int count) {
        if(stockQuantity >= count) {
            this.stockQuantity -= count;
        } else {
            throw new RuntimeException("재고가 부족합니다.");
        }
    }

    public void increase(int count) {
        this.stockQuantity += count;
    }
}
