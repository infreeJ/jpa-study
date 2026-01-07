package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.Status;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findAllByMember_MemberId(Long memberId);

    List<Order> searchOrders(String name, Status status);
}
