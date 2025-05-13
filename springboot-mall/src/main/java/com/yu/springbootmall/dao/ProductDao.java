package com.yu.springbootmall.dao;

import com.yu.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
