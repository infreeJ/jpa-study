package com.example.demo.controller;

import com.example.demo.dto.request.SignupRequest;
import com.example.demo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MemberController {

    private final MemberService memberService;

    @Operation(description = "회원가입 API")
    @PostMapping("/member")
    public void signup(@RequestBody SignupRequest signupRequest) {

        memberService.signup(signupRequest);
    }
}
