package com.java.yh.server;

import com.java.yh.domain.Address;

import java.util.List;

public interface AddressService {
    Boolean setAddress(Address address);

    List<Address> QueryAddress(int uid);
}
