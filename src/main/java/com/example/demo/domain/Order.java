package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private int totalPrice;

    @Column
    @CreatedDate
    private LocalDateTime orderDate;

    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PAYMENT;
}
