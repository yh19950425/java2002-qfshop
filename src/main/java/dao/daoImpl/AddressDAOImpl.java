package com.java.yh.dao.daoImpl;

import com.java.yh.dao.AddressDAO;
import com.java.yh.domain.Address;
import com.java.yh.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    @Override
    public int setAddress(Address address) {
        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();

        String sql="insert into tb_address (detail,name,phone,uid) values(?,?,?,?)";

        int update = jdbcTemplate.update(sql,
                address.getDetail(),
                address.getName(),
                address.getPhone(),
                address.getUid());

        return update;


    }

    @Override
    public List<Address> QueryAddress(int uid) {
        JdbcTemplate jdbcTemplate = DataSourceUtils.getjdbcTemplate();
        String sql="select * from tb_address where uid=?";
        List<Address> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class), uid);
        return query;
    }
}