package com.turkcell.spring.controllers;

import com.turkcell.spring.business.abstracts.OrderService;
import com.turkcell.spring.entities.dtos.order.OrderForAddDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping("add")
    public void add(@RequestBody @Valid OrderForAddDto request){
        orderService.add(request);
    }

}
