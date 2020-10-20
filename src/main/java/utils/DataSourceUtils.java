package com.java.yh.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceUtils implements ServletContextListener {
    private static DataSource ds;
    private static JdbcTemplate jdbcTemplate;
    public static DataSource getDataSource(){
        return ds;
    }
    public static JdbcTemplate getjdbcTemplate(){ return jdbcTemplate;
    }


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        InputStream is = servletContext.getResourceAsStream("WEB-INF/database.properties");

        Properties properties = new Properties();

        try {
            properties.load(is);

            ds= DruidDataSourceFactory.createDataSource(properties);
            jdbcTemplate = new JdbcTemplate(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }






    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}