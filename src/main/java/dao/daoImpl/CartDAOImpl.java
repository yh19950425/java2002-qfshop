package com.java.yh.dao.daoImpl;

import com.java.yh.dao.CartDAO;
import com.java.yh.domain.Cart;
import com.java.yh.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CartDAOImpl implements CartDAO {
    @Override
    public int addCard(int id, String goodsId, String price,int pNum) {
        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="insert into tb_cart(uid,pid,money,Num) values (?,?,?,?)";





        int update = jdbcTemplate.update(sql, id, goodsId, price,pNum);

        return update;


    }

    @Override
    public List<Cart> QueryCart(int uid) {

        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="select tb_cart.*, tb_goods.name as goodName from tb_cart inner join tb_goods on tb_goods.id=tb_cart.pid where uid=?";

        List<Cart> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class),uid);

        return query;


    }
}