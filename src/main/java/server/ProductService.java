package com.java.yh.server;

import com.java.yh.domain.Category;
import com.java.yh.domain.Product;

import java.util.List;

public interface ProductService {
    int addProduct(Product product);

    List<Product> QueryProduct();

    List<Category> QueryProductType();

    List<Product> QueryByTypeId(String id);
}
