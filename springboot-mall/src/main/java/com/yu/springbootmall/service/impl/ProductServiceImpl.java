package com.yu.springbootmall.service.impl;

import com.yu.springbootmall.constant.ProductCategory;
import com.yu.springbootmall.dao.ProductDao;
import com.yu.springbootmall.dto.ProductQueryParams;
import com.yu.springbootmall.dto.ProductRequest;
import com.yu.springbootmall.model.Product;
import com.yu.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProdcut(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
