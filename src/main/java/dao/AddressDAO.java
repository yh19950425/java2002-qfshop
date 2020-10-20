package com.java.yh.dao;

import com.java.yh.domain.Address;

import java.util.List;

public interface AddressDAO {
    int setAddress(Address address);

    List<Address> QueryAddress(int uid);
}
