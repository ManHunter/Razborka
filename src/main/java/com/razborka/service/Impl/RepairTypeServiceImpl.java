package com.razborka.service.Impl;

import com.razborka.dao.RepairTypeDao;
import com.razborka.model.RepairType;
import com.razborka.service.RepairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 19.04.2015.
 */
@Service
@Transactional
public class RepairTypeServiceImpl implements RepairTypeService {

    @Autowired
    private RepairTypeDao repairTypeDao;

    @Override
    public void saveRepairType(RepairType repairType) {
        repairTypeDao.save(repairType);
    }

    @Override
    public void updateRepairType(RepairType repairType) {
        repairTypeDao.update(repairType);
    }

    @Override
    public void deleteRepairType(int id) {
        repairTypeDao.deleteById(id);
    }

    @Override
    public List<RepairType> getAllRepairType() {
        return repairTypeDao.getAll();
    }

    @Override
    public RepairType getRepairTypeById(int id) {
        return repairTypeDao.get(id);
    }

    @Override
    public List<RepairType> getAllRepairTypeExceptUser(int user_id) {
        return repairTypeDao.getAllRepairTypeExceptUser(user_id);
    }
}
