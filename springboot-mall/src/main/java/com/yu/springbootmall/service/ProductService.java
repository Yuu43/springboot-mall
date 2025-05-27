package com.yu.springbootmall.service;

import com.yu.springbootmall.dto.ProductQueryParams;
import com.yu.springbootmall.dto.ProductRequest;
import com.yu.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

        Integer countProduct(ProductQueryParams productQueryParams);

        List<Product> getProducts(ProductQueryParams productQueryParams);

        Product getProductById(Integer productId);

        Integer createProduct(ProductRequest productRequest);

        void updateProdcut(Integer productId, ProductRequest productRequest);

        void deleteProductById(Integer productId);
}
