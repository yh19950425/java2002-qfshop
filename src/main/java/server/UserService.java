package com.java.yh.server;

import com.java.yh.domain.User;

import java.util.List;

public interface UserService {
    boolean QueryUsername(String checkusername);

    boolean QueryEmail(String email);

    boolean add(User user);

    boolean queryCode(String code);

    User QueryUser(String username, String password);

    User QueryAdmin(String username, String password);

}