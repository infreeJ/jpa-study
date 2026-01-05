package com.example.demo.repository;

import com.example.demo.domain.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findAllByMember_MemberId(Long memberId);
}
