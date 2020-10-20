package com.java.yh.server;

import com.java.yh.domain.Order;
import com.java.yh.domain.User;

public interface OrderService {
    boolean addOrder(Order order);

    int QueryMoney(int uid);
}
