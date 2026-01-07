package com.example.demo.repository;

import com.example.demo.domain.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.demo.domain.QOrder.order;
import static com.example.demo.domain.QOrderItem.orderItem;
import static com.example.demo.domain.QItem.item;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllByMember_MemberId(Long memberId) {

        List<Order> list = queryFactory
                .selectFrom(order)
                .join(order.orderItems, orderItem).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .where(order.member.memberId.eq(memberId))
                .fetch();

        return list;
    }

    @Override
    public List<Order> searchOrders(String name, Status status) {

        List<Order> list = queryFactory
                .selectFrom(order)
                .join(order.orderItems, orderItem).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .where(nameEqual(name), statusEqual(status))
                .fetch();
        return list;
    }

    private BooleanExpression nameEqual(String name) {
        if(StringUtils.hasText(name)) {
            return order.member.name.eq(name);
        } else {
            return null;
        }
    }

    private BooleanExpression statusEqual(Status status) {
        if(status == null) {
            return null;
        } else {
            return order.status.eq(status);
        }
    }
}




