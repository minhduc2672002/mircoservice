package com.example.orderservice.controller;


import com.example.orderservice.dto.request.OrderRequest;

import com.example.orderservice.model.OrderLineItem;

import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){
//        try{
//            orderService.placeOrder(orderRequest);
//            return new ResponseEntity<>("Order is placed",HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>("Place order failed",HttpStatus.CREATED);
//        }
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>("Order is placed",HttpStatus.CREATED);
    }
}
