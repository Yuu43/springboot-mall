package com.yu.springbootmall.dao;

import com.yu.springbootmall.constant.ProductCategory;
import com.yu.springbootmall.dto.ProductQueryParams;
import com.yu.springbootmall.dto.ProductRequest;
import com.yu.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
