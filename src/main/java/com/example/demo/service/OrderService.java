package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

        Map<Long, Integer> orderMap = new HashMap<>();
        for(int i = 0; i < orderRequest.getItemId().size(); i++) {
            orderMap.put(orderRequest.getItemId().get(i), orderRequest.getCount().get(i));
        }

        List<Long> sortedItemIds = new ArrayList<>(orderMap.keySet());
        sortedItemIds.sort(Comparator.naturalOrder());

        for (Long itemId : sortedItemIds) {
            Item item = itemRepository.findByIdWithPessimisticLock(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다."));

            int count = orderMap.get(itemId);
            item.decrease(count);

            int orderPrice = count * item.getPrice();
            totalPrice += orderPrice;

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .item(item)
                    .orderPrice(orderPrice)
                    .count(count)
                    .build();

            order.addOrderItem(orderItem);
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문 정보를 찾을 수 없습니다."));

        order.updateStatus(Status.CANCEL);

        List<OrderItem> orderItems = order.getOrderItems();

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);

            int count = orderItem.getCount();

            Item item = orderItem.getItem();
            item.increase(count);
        }
    }

    @Transactional
    public List<OrderResponse> findMemberOrders(Long memberId) {
        List<Order> orders = orderRepository.findAllByMember_MemberId(memberId);

        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(Order order : orders) {
            OrderResponse orderResponse = OrderResponse.from(order);
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }

    @Transactional
    public List<OrderResponse> searchOrders(String name, Status status) {
        List<Order> orders = orderRepository.searchOrders(name, status);

        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(Order order : orders) {
            OrderResponse orderResponse = OrderResponse.from(order);
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }
}



