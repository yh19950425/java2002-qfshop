package com.java.yh.server;

import com.java.yh.domain.Cart;
import com.java.yh.domain.Product;
import com.java.yh.domain.User;

import java.util.List;

public interface CartService {
    boolean addCard(int userId,String goodsId,String price,int pNum);

    List<Cart> QueryCart(int uid);
}
