package com.example.demo.controller;

import com.example.demo.dto.request.ItemRegistrationRequest;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item")
    public void registration(@RequestBody ItemRegistrationRequest itemRegistrationRequest) {
        itemService.Registration(itemRegistrationRequest);
    }
}
