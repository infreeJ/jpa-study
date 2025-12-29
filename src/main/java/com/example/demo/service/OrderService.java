package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.request.OrderRequest;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public void order(OrderRequest orderRequest) {

        Member member = memberRepository.findById(orderRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));


    }
}
