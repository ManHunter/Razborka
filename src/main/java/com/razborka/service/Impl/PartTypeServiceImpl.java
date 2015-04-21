package com.razborka.service.Impl;

import com.razborka.dao.PartTypeDao;
import com.razborka.model.PartType;
import com.razborka.service.PartTypeService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Service
@Transactional
public class PartTypeServiceImpl implements PartTypeService {

    @Autowired
    private PartTypeDao partTypeDao;

    @Override
    public void savePartType(PartType part) {
        partTypeDao.save(part);
    }

    @Override
    public void updatePartType(PartType part) {
        partTypeDao.update(part);
    }

    @Override
    public void deletePartType(int id) {
        partTypeDao.deleteById(id);
    }

    @Override
    public List<PartType> getAllPartType() {
        return partTypeDao.getAll();
    }

    @Override
    public PartType getPartTypeById(int id) {
        return partTypeDao.get(id);
    }

    @Override
    public List<PartType> getPartTypeByGroupId(int groupId) {
        return partTypeDao.getPartTypeByGroupId(groupId);
    }
}
