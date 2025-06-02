package com.yu.springbootmall.service.impl;

import com.yu.springbootmall.dao.OrderDao;
import com.yu.springbootmall.dao.ProductDao;
import com.yu.springbootmall.dto.BuyItem;
import com.yu.springbootmall.dto.CreateOrderRequest;
import com.yu.springbootmall.model.Order;
import com.yu.springbootmall.model.OrderItem;
import com.yu.springbootmall.model.Product;
import com.yu.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);
        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int tatalAmount = 0;
        List<OrderItem> orderItemsList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // 計算總價格
            int amount = buyItem.getQuantity() * product.getPrice();
            tatalAmount = tatalAmount + amount;

            // 轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemsList.add(orderItem);
        }


        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, tatalAmount);

        orderDao.createOrderItems(orderId, orderItemsList);

        return orderId;
    }
}
