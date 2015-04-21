package com.razborka.service.Impl;

import com.razborka.dao.FuelDao;
import com.razborka.model.Fuel;
import com.razborka.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Service
@Transactional
public class FuelServiceImpl implements FuelService {

    @Autowired
    private FuelDao fuelDao;

    @Override
    public void saveFuel(Fuel fuel) {
        fuelDao.save(fuel);
    }

    @Override
    public List<Fuel> getAllFuel() {
        return fuelDao.getAll();
    }

    @Override
    public void deleteFuel(int id) {
        fuelDao.deleteById(id);
    }

    @Override
    public Fuel getFuelById(int id) {
        return fuelDao.get(id);
    }
}
