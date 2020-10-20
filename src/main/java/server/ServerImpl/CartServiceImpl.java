package com.java.yh.server.ServerImpl;

import com.java.yh.dao.CartDAO;
import com.java.yh.dao.daoImpl.CartDAOImpl;
import com.java.yh.domain.Cart;
import com.java.yh.domain.Product;
import com.java.yh.domain.User;
import com.java.yh.server.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartDAO dao=new CartDAOImpl();

    @Override
    public boolean addCard(int id, String goodsId, String price,int pNum) {
        int addcard=dao.addCard(id,goodsId,price,pNum);

        return true;
    }

    @Override
    public List<Cart> QueryCart(int uid) {
        List<Cart> carts = dao.QueryCart(uid);


        return carts;
    }
}