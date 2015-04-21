package com.razborka.service;

import com.razborka.model.Fuel;

import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
public interface FuelService {

    public void saveFuel(Fuel fuel);

    public List<Fuel> getAllFuel();

    public void deleteFuel(int id);

    public Fuel getFuelById(int id);
}
