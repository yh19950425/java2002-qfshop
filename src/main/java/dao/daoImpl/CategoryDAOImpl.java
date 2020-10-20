package com.java.yh.dao.daoImpl;

import com.java.yh.dao.CategoryDAO;
import com.java.yh.domain.Category;
import com.java.yh.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    JdbcTemplate jdbcTemplate=new JdbcTemplate(DataSourceUtils.getDataSource());

    @Override
    public List<Category> queryAll() {
        String sql="select * from tb_goods_type";

        List<Category> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));

        return query;
    }

    @Override
    public int insert(Category category) {
        String sql="insert into tb_goods_type (name,level,parent) values(?,?,?)";

        int update = jdbcTemplate.update(sql, category.getName(), category.getLevel(), category.getParent());



        String lastIdSql = "SELECT last_insert_id()";

        String lastId = jdbcTemplate.queryForObject(lastIdSql, String.class);

        category.setId(lastId);


        return update;
    }
}