package com.razborka.service.Impl;

import com.razborka.dao.BodyTypeDao;
import com.razborka.model.Body;
import com.razborka.service.BodyTypeService;
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
public class BodyTypeServiceImpl implements BodyTypeService {

    @Autowired
    private BodyTypeDao bodyTypeDao;

    @Override
    public void saveBody(Body body) {
        bodyTypeDao.save(body);
    }

    @Override
    public void updateBody(Body body) {
        bodyTypeDao.update(body);
    }

    @Override
    public void deleteBody(int id) {
        bodyTypeDao.deleteById(id);
    }

    @Override
    public List<Body> getAllBody() {
        return bodyTypeDao.getAll();
    }

    @Override
    public Body getBodyById(int id) {
        return bodyTypeDao.get(id);
    }
}
