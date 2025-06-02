package com.yu.springbootmall.service;

import com.yu.springbootmall.dto.CreateOrderRequest;
import com.yu.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
