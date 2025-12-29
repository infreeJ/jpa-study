package com.example.demo.service;

import com.example.demo.dto.request.ItemRegistrationRequest;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void Registration(ItemRegistrationRequest itemRegistrationRequest) {


    }
}
