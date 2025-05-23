package com.yu.springbootmall.dao;

import com.yu.springbootmall.dto.ProductRequest;
import com.yu.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
