package com.java.yh.dao;

import com.java.yh.domain.User;

import java.util.List;

public interface UserDAO {

    boolean QueryUsername(String checkusername);

    boolean QueryEmail(String email);

    int add(User user);

    int queryCode(String code);

    User QueryUser(String username,String password);

    User QueryAdmin(String username, String password);

}