package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.QItem;
import com.example.demo.domain.QOrder;
import com.example.demo.domain.QOrderItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllByMember_MemberId(Long memberId) {

        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;
        QItem item = QItem.item;

        List<Order> list = queryFactory
                .selectFrom(order)
                .join(order.orderItems, orderItem).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .where(order.member.memberId.eq(memberId))
                .fetch();

        return list;
    }
}
