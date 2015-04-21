package com.razborka.service;

import com.razborka.model.RepairType;

import java.util.List;

/**
 * Created by Admin on 19.04.2015.
 */
public interface RepairTypeService {
    public void saveRepairType(RepairType repairType);

    public void updateRepairType(RepairType repairType);

    public void deleteRepairType(int id);

    public List<RepairType> getAllRepairType();

    public RepairType getBodyById(int id);
}
