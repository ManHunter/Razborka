package com.razborka.service.Impl;

import com.razborka.dao.CarDao;
import com.razborka.dao.PartDao;
import com.razborka.model.Car;
import com.razborka.model.Part;
import com.razborka.service.CarService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 14.04.2015.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public void saveCar(Car car) {
        carDao.save(car);
    }

    @Override
    public void updateCar(Car car) {
        carDao.update(car);
    }

    @Override
    public void deleteCar(int id) {
        carDao.deleteById(id);
    }

    @Override
    public List<Car> getAllCar() {
        return carDao.getAll();
    }

    @Override
    public Car getCarById(int id) {
        return carDao.get(id);
    }

    @Override
    public List<Car> getAllUserCars(int user_id) {
        return carDao.getAllUserCars(user_id);
    }

    @Override
    public int countPartByCarId(int car_id) {
        carDao.countPartByCarId(car_id);

        return 0;
    }
}
