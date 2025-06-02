package com.yu.springbootmall.controller;

import com.yu.springbootmall.dao.UserDao;
import com.yu.springbootmall.dto.CreateOrderRequest;
import com.yu.springbootmall.model.Order;
import com.yu.springbootmall.model.User;
import com.yu.springbootmall.service.OrderService;
import com.yu.springbootmall.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserDao userDao;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder (@PathVariable Integer userId,
                                          @RequestBody @Valid CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
