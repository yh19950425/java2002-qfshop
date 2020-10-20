package com.java.yh.dao;

import com.java.yh.domain.Order;

public interface OrderDAO {
    int addOrder(Order order);

    int QueryMoney(int uid);
}
