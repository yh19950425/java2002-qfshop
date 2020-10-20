package com.java.yh.server.ServerImpl;

import com.java.yh.dao.UserDAO;
import com.java.yh.dao.daoImpl.UserDAOImpl;
import com.java.yh.domain.User;
import com.java.yh.server.UserService;

public class UserServiceImpl implements UserService {
    UserDAO dao=new UserDAOImpl();

    @Override
    public boolean QueryUsername(String checkusername) {
       return dao.QueryUsername(checkusername);
    }

    @Override
    public boolean QueryEmail(String email) {
        return dao.QueryEmail(email);
    }

    @Override
    public boolean add(User user) {
        int i=dao.add(user);
        return true;
    }

    @Override
    public boolean queryCode(String code) {
        int i = dao.queryCode(code);
        return true;
    }

    @Override
    public User QueryUser(String username, String password) {
        User b = dao.QueryUser(username, password);
        return b;
    }

    @Override
    public User QueryAdmin(String username, String password) {
        User num=dao.QueryAdmin(username,password);
        return num;

    }
}