package com.java.yh.dao.daoImpl;

import com.java.yh.dao.UserDAO;
import com.java.yh.domain.User;
import com.java.yh.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtils.getDataSource());


    @Override
    public boolean QueryUsername(String checkusername) {
        String sql = "select * from tb_user where username=?";

        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), checkusername);
        if (query.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean QueryEmail(String email) {
        String sql = "select * from tb_user where email=?";

        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
        if (query.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int add(User user) {
        String sql = "insert into tb_user (username,password,email,gender,code) values(?,?,?,?,?)";

        int update = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getGender(), user.getCode());

        return update;
    }

    @Override
    public int queryCode(String code) {
        String sql = "update tb_user set flag=2 where code=?";

        int update = jdbcTemplate.update(sql, code);

        return update;
    }

    @Override
    public User QueryUser(String username, String password) {

        String sql = "select * from tb_user where username=? and password=? and flag=2";
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        if (query.size() == 1) {
            return query.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User QueryAdmin(String username, String password) {
        String sql="select * from tb_user where username=? and password=? and role=1";
        List<User> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        if (query.size() == 1) {
            return query.get(0);
        } else {
            return null;
        }


    }
}