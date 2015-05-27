package com.razborka.service;

import com.razborka.model.Address;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
public interface AddressService {

    public void saveAddress(Address address);

    public void updateAddress(Address address);

    public void deleteAddress(int id);

    public List<Address> getAddressByUserId(int user_id);

    public List<Address> getAddressByUserRole(String role);

    public List<String> getAllCities();
}
