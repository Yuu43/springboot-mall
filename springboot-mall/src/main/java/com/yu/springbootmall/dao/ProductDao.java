package com.yu.springbootmall.dao;

import com.yu.springbootmall.constant.ProductCategory;
import com.yu.springbootmall.dto.ProductRequest;
import com.yu.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
