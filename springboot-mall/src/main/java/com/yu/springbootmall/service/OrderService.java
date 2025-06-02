package com.yu.springbootmall.service;

import com.yu.springbootmall.dto.CreateOrderRequest;
import com.yu.springbootmall.dto.OrderQueryParams;
import com.yu.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
