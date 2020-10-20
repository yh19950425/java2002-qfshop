package com.java.yh.server.ServerImpl;

import com.java.yh.dao.OrderDAO;
import com.java.yh.dao.daoImpl.OrderDAOImpl;
import com.java.yh.domain.Order;
import com.java.yh.server.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDAO dao=new OrderDAOImpl();
    @Override
    public boolean addOrder(Order order) {
        int a=dao.addOrder(order);
        return true;
    }

    @Override
    public int QueryMoney(int uid) {
        int money=dao.QueryMoney(uid);
        return money;
    }
}