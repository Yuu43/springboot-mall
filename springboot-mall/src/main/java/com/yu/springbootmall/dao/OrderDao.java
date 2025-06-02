package com.yu.springbootmall.dao;

import com.yu.springbootmall.dto.CreateOrderRequest;
import com.yu.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemsList);
}
