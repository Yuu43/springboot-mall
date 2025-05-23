package com.yu.springbootmall.service;

import com.yu.springbootmall.dto.ProductRequest;
import com.yu.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

        List<Product> getProducts();

        Product getProductById(Integer productId);

        Integer createProduct(ProductRequest productRequest);

        void updateProdcut(Integer productId, ProductRequest productRequest);

        void deleteProductById(Integer productId);
}
