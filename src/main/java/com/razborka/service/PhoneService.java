package com.razborka.service;

import com.razborka.model.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
@Service("phoneService")
@Transactional
public interface PhoneService {
    public void savePhone(Phone phone);
    public void updatePhone(Phone phone);
    public void deletePhone(int id);
    public List<Phone> getPhoneByUserId(int user_id);
}
