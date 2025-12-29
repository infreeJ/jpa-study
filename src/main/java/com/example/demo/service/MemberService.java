package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.request.SignupRequest;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(SignupRequest signupRequest) {

        Member member = signupRequest.toEntity();

        memberRepository.save(member);
    }
}
