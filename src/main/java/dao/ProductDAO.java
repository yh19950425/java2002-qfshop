package com.java.yh.dao;

import com.java.yh.domain.Category;
import com.java.yh.domain.Product;

import java.util.List;

public interface ProductDAO {
    int addProduct(Product product);

    List<Product> QueryGoods();

    List<Category> QueryProductType();

    List<Product> QueryByTypeId(String id);
}
