package com.razborka.dao;

import com.razborka.model.Car;
import com.razborka.model.Part;
import com.razborka.model.User;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PartDao extends Dao<Part> {
    public List<Part> getAllUserPart(int user_id);
    public List<Part> getAllPartsCar(int car_id);
}
