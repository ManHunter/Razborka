package com.razborka.dao;

import com.razborka.model.Address;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
public interface AddressDao extends Dao<Address> {
    public List<Address> getAddressByUserId(int user_id);
}
