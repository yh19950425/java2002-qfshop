package com.java.yh.dao.daoImpl;

import com.java.yh.dao.ProductDAO;
import com.java.yh.domain.Category;
import com.java.yh.domain.Product;
import com.java.yh.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public int addProduct(Product product) {
        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="insert into tb_goods (name,pubdate,picture,price,star,intro,typeid) values(?,?,?,?,?,?,?)";

        int update = jdbcTemplate.update(sql, product.getName(),
                product.getPubdate(),
                product.getPicture(),
                product.getPrice(),
                product.getStar(),
                product.getIntro(),
                product.getTypeid());

        return update;
    }

    @Override
    public List<Product> QueryGoods() {

        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="select * from tb_goods";

        List<Product> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));

        return query;
    }

    @Override
    public List<Category> QueryProductType() {

        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="select * from tb_goods_type";

        List<Category> productType = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));

        return productType;
    }

    @Override
    public List<Product> QueryByTypeId(String id) {
        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();
        String sql="select * from tb_goods where id=?";
        List<Product> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), id);

        return query;
    }
}