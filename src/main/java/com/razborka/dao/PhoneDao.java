package com.razborka.dao;

import com.razborka.model.Phone;
import com.razborka.model.Photo;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
public interface PhoneDao extends Dao<Phone> {
    public List<Phone> getPhoneByUserId(int user_id);
}
