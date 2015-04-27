package com.razborka.dao;

import com.razborka.model.Car;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface CarDao extends Dao<Car> {
    public int countPartByCarId(int car_id);
    public List<Car> getAllUserCars(int user_id);
}
