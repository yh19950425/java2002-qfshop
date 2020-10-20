package com.java.yh.server.ServerImpl;

import com.java.yh.dao.AddressDAO;
import com.java.yh.dao.daoImpl.AddressDAOImpl;
import com.java.yh.domain.Address;
import com.java.yh.server.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    AddressDAO dao=new AddressDAOImpl();


    @Override
    public Boolean setAddress(Address address) {

        int addresses=dao.setAddress(address);

        return true;

    }

    @Override
    public List<Address> QueryAddress(int uid) {
        List<Address> addresses=dao.QueryAddress(uid);
        return addresses;
    }
}