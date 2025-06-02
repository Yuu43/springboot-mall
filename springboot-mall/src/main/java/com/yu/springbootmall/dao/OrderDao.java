package com.yu.springbootmall.dao;

import com.yu.springbootmall.dto.CreateOrderRequest;
import com.yu.springbootmall.dto.OrderQueryParams;
import com.yu.springbootmall.model.Order;
import com.yu.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemsList);
}
