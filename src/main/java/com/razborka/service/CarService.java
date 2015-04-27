package com.razborka.service;

import com.razborka.model.Car;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface CarService {

    public void saveCar(Car engine);

    public void updateCar(Car engine);

    public void deleteCar(int id);

    public List<Car> getAllCar();

    public Car getCarById(int id);

    public List<Car> getAllUserCars(int user_id);

    public int countPartByCarId(int car_id);
}
