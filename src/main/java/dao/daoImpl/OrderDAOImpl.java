package com.java.yh.dao.daoImpl;

import com.java.yh.dao.OrderDAO;
import com.java.yh.domain.Order;
import com.java.yh.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public int addOrder(Order order) {

        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="insert into tb_order(uid,money,aid,status) values(?,?,?,?)";

        int update = jdbcTemplate.update(sql, order.getUid(), order.getMoney(), 1, 0);

        return update;


    }

    @Override
    public int QueryMoney(int uid) {
        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="select sum(money) from tb_cart where id=?";

        int money = jdbcTemplate.queryForObject(sql, Integer.class, uid);

        return money;


    }
}