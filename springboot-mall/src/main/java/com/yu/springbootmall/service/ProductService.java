package com.yu.springbootmall.service;

import com.yu.springbootmall.dto.ProductRequest;
import com.yu.springbootmall.model.Product;

public interface ProductService {

        Product getProductById(Integer productId);

        Integer createProduct(ProductRequest productRequest);

        void updateProdcut(Integer productId, ProductRequest productRequest);
}
