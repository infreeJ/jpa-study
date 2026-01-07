package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.Member;
import com.example.demo.dto.request.OrderRequest;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    // 테스트가 끝나면 데이터 정리
    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
        memberRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("동시에 100명이 주문을 하면 재고가 0이 되어야 한다.")
    public void concurrencyTest() throws InterruptedException {

        // given
        // 재고 100개짜리 상품 생성
        Item item = Item.builder()
                .name("맥북")
                .price(1000000)
                .stockQuantity(100) // 재고 100개
                .build();
        itemRepository.save(item);

        // 회원 생성
        Member member = Member.builder()
                .name("tester")
                .build();
        memberRepository.save(member);

        int threadCount = 100; // 100명이 동시 접속한다고 가정
        // 비동기로 실행 작업을 도와주는 자바의 API (32개의 쓰레드 풀 생성)
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 100개의 요청이 끝날 때까지 기다리게 해주는 도구 (LATCH가 100에서 시작해서 하나씩 줄어든다)
        CountDownLatch latch = new CountDownLatch(threadCount);

        // Request 구성
        OrderRequest orderRequest = OrderRequest.builder()
                .memberId(member.getMemberId())
                .itemId(List.of(item.getItemId()))
                .count(List.of(1))
                .build();

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    // 1개 주문 시도
                    orderService.order(orderRequest);
                } finally {
                    latch.countDown(); // 작업이 끝나면 카운트 1 감소
                }
            });
        }

        latch.await(); // 테스트가 끝나지 않도록 카운트가 0이 될 때까지 메인 스레드 대기

        // then
        Item findItem = itemRepository.findById(item.getItemId()).orElseThrow();

        // 기대값: 100 - (1*100) = 0개
        assertEquals(0, findItem.getStockQuantity());
    }
}