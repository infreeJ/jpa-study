package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.dto.request.OrderRequest;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public void order(OrderRequest orderRequest) {

        Member member = memberRepository.findById(orderRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        Item item = itemRepository.findById(orderRequest.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다."));

        Order order = Order.builder()
                .member(member)
                .build();

        int orderPrice = orderRequest.getCount() * item.getPrice();

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .item(item)
                .orderPrice(orderPrice)
                .count(orderRequest.getCount())
                .build();

        orderRepository.save(order);
        orderItemRepository.save(orderItem);
    }
}
