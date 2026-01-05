package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

//    @Query("""
//    SELECT o
//    FROM Order o
//    JOIN FETCH o.orderItems oi
//    JOIN FETCH oi.item i
//    WHERE o.member.memberId = :memberId
//    """)
//    List<Order> findAllByMember_MemberId(Long memberId);
}
