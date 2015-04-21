package com.razborka.service.Impl;

import com.razborka.dao.PhoneDao;
import com.razborka.model.Phone;
import com.razborka.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
@Service("phoneService")
@Transactional
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDao phoneDao;


    @Override
    public void savePhone(Phone phone) {
        phoneDao.save(phone);
    }

    @Override
    public void updatePhone(Phone phone) {
        phoneDao.update(phone);
    }

    @Override
    public void deletePhone(int id) {
        phoneDao.deleteById(id);
    }

    @Override
    public List<Phone> getPhoneByUserId(int user_id) {
        return phoneDao.getPhoneByUserId(user_id);
    }
}
