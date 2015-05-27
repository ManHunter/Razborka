package com.razborka.service;

import com.razborka.model.Car;
import com.razborka.model.Part;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PartService {

    public void savePart(Part part);

    public void updatePart(Part part);

    public void deletePart(int id);

    public List<Part> getAllPart();

    public List<Part> getAllPart(int page);

    public Part getPartById(int id);

    public List<Part> getAllUserPart(int user_id);

    public List<Part> getAllPartsCar(int car_id);

    public List<Part> getUserPartsByCar(int user_id, int car_id, int page);

    public List<Part> getPartsByOtherSeller(int user_id, Car car);

    public List<Part> partFilter(Integer user_id, int brand_id, int model_id, int year, int volume, int fuel_id, int body_id, int part_group_id, int part_type_id, String city, int page);

    public int numberOfParts(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id, int body_id, int part_group_id, int part_type_id, String city);

    public int numberOfParts(int user_id, int car_id);
}
