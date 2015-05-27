package com.razborka.dao;

import com.razborka.model.Service;

import java.util.List;

/**
 * Created by Admin on 18.04.2015.
 */
public interface ServiceDao extends Dao<Service> {

    public List<Service> getAllUserServices(int user_id);

}
