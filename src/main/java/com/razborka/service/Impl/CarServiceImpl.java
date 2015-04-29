package com.razborka.service.Impl;

import com.razborka.dao.CarDao;
import com.razborka.dao.PartDao;
import com.razborka.model.Car;
import com.razborka.model.Part;
import com.razborka.service.CarService;
import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 14.04.2015.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    public void saveCar(Car car) {
        car.setDate(LocalDateTime.now());
        carDao.save(car);
    }

    public void saveCar(Car car, MultipartFile file, String path) {
        try {
            String filename = generateFilename(file.getOriginalFilename());
            File f = new File(path + "resources\\image\\car\\" + filename);
            FileUtils.writeByteArrayToFile(f, file.getBytes());
            car.setPhoto(filename);
            saveCar(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCar(Car car) {
        carDao.update(car);
    }

    public void updateCar(Car car, MultipartFile file, String path) {
        try {
            String filename = generateFilename(file.getOriginalFilename());
            File f = new File(path + "resources\\image\\car\\" + filename);
            FileUtils.writeByteArrayToFile(f, file.getBytes());
            car.setPhoto(filename);
            updateCar(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int id) {
        carDao.deleteById(id);
    }

    public List<Car> getAllCar() {
        return carDao.getAll();
    }

    public Car getCarById(int id) {
        return carDao.get(id);
    }

    public List<Car> getAllUserCars(int user_id) {
        return carDao.getAllUserCars(user_id);
    }

    public int countPartByCarId(int car_id) {
        carDao.countPartByCarId(car_id);

        return 0;
    }

    public Car getCarByPhoto(String photo_name) {
        return carDao.getCarByPhoto(photo_name);
    }

    public String generateFilename(String originalFilename) {
        Long nameRandom = Calendar.getInstance().getTimeInMillis();
        originalFilename = nameRandom + "_" + originalFilename;
        return originalFilename;
    }
}
