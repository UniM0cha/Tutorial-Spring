package com.example.spring_gibon.service;

import com.example.spring_gibon.domain.Order;

public interface OrderService {

    public Order createOrder(Long id, String name, Integer price);
}
