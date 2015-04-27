package com.razborka.service.Impl;

import com.razborka.dao.PartDao;
import com.razborka.model.Part;
import com.razborka.model.User;
import com.razborka.service.PartService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
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
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    @Override
    public void savePart(Part part) {
        part.setDate(LocalDateTime.now());
        partDao.save(part);
    }

    @Override
    public void updatePart(Part part) {
        partDao.update(part);
    }

    @Override
    public void deletePart(int id) {
        partDao.deleteById(id);
    }

    @Override
    public List<Part> getAllPart() {
        return partDao.getAll();
    }

    @Override
    public Part getPartById(int id) {
        return partDao.get(id);
    }

    @Override
    public List<Part> getAllUserPart(int user_id) {
        return partDao.getAllUserPart(user_id);
    }

    @Override
    public List<Part> getAllPartsCar(int car_id) {
        return partDao.getAllPartsCar(car_id);
    }


}
