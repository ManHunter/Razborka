package com.razborka.service.Impl;

import com.razborka.dao.AddressDao;
import com.razborka.model.Address;
import com.razborka.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public void saveAddress(Address address) {
        addressDao.save(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.update(address);
    }

    @Override
    public void deleteAddress(int id) {
        addressDao.deleteById(id);
    }

    @Override
    public List<Address> getAddressByUserId(int user_id) {
        return addressDao.getAddressByUserId(user_id);
    }

    @Override
    public List<Address> getAddressByUserRole(String role) {
        return addressDao.getAddressByUserRole(role);
    }

    public List<String> getAllCities() {
        return addressDao.getAllCities();
    }
}
