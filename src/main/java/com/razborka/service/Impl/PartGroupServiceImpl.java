package com.razborka.service.Impl;

import com.razborka.dao.PartGroupDao;
import com.razborka.dao.PhotoDao;
import com.razborka.model.PartGroup;
import com.razborka.service.PartGroupService;
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
public class PartGroupServiceImpl implements PartGroupService {

    @Autowired
    private PartGroupDao partGroupDao;

    @Override
    public void savePartGroup(PartGroup part) {
        partGroupDao.save(part);
    }

    @Override
    public void updatePartGroup(PartGroup part) {
        partGroupDao.update(part);
    }

    @Override
    public void deletePartGroup(int id) {
        partGroupDao.deleteById(id);
    }

    @Override
    public List<PartGroup> getAllPartGroup() {
        return partGroupDao.getAll();
    }

    @Override
    public PartGroup getPartGroupById(int id) {
        return partGroupDao.get(id);
    }
}
