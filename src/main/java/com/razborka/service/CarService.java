package com.razborka.service;

import com.razborka.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface CarService {

    public void saveCar(Car car);

    public void saveCar(Car car, MultipartFile file, String path);

    public void updateCar(Car car);

    public void updateCar(Car car, MultipartFile file, String path);

    public void deleteCar(int id);

    public List<Car> getAllCar();

    public Car getCarById(int id);

    public List<Car> getAllUserCars(int user_id);

    public List<Car> getUserCars(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id,
                                 int body_id, int page);

    public int numberOfCars(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id, int body_id);

    public int countPartByCarId(int car_id);

    public Car getCarByPhoto(String photo_name);

    public List<Integer> getAllEngineVolume();
}
