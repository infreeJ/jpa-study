package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.dto.request.OrderRequest;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void order(OrderRequest orderRequest) {

        Member member = memberRepository.findById(orderRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        
        Order order = Order.builder()
                .member(member)
                .totalPrice(0)
                .build();


        int totalPrice = 0;

        for (int i = 0; i < orderRequest.getItemId().size(); i++) {
            Item item = itemRepository.findById(orderRequest.getItemId().get(i))
                    .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다."));

            int count = orderRequest.getCount().get(i);
            item.decrease(count);

            int orderPrice = count * item.getPrice();
            totalPrice += orderPrice;

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .item(item)
                    .orderPrice(orderPrice)
                    .count(orderRequest.getCount().get(i))
                    .build();

            order.addOrderItem(orderItem);
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }
}
