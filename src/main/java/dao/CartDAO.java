package com.java.yh.dao;

import com.java.yh.domain.Cart;

import java.util.List;

public interface CartDAO {
    int addCard(int userId, String goodsId, String price,int pNum);

    List<Cart> QueryCart(int uid);
}
