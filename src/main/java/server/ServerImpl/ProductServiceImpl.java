package com.java.yh.server.ServerImpl;

import com.java.yh.dao.ProductDAO;
import com.java.yh.dao.daoImpl.ProductDAOImpl;
import com.java.yh.domain.Category;
import com.java.yh.domain.Product;
import com.java.yh.server.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDAO dao=new ProductDAOImpl();
    @Override
    public int addProduct(Product product) {
        return dao.addProduct(product);
    }

    @Override
    public List<Product> QueryProduct() {
        List<Product> goodsList=dao.QueryGoods();
        return goodsList;
    }

    @Override
    public List<Category> QueryProductType() {
        List<Category> goodsListType=dao.QueryProductType();
        return goodsListType;
    }

    @Override
    public List<Product> QueryByTypeId(String id) {
        List<Product> products=dao.QueryByTypeId(id);
        return products;
    }
}