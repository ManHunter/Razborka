package com.razborka.service.Impl;

import com.razborka.dao.KppDao;
import com.razborka.model.Kpp;
import com.razborka.service.KppService;
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
public class KppServiceImpl implements KppService {

    @Autowired
    private KppDao kppDao;

    @Override
    public void saveKpp(Kpp kpp) {
        kppDao.save(kpp);
    }

    @Override
    public void updateKpp(Kpp kpp) {
        kppDao.update(kpp);
    }

    @Override
    public void deleteKpp(int id) {
        kppDao.deleteById(id);
    }

    @Override
    public List<Kpp> getAllKpp() {
        return kppDao.getAll();
    }

    @Override
    public Kpp getKppById(int id) {
        return kppDao.get(id);
    }
}
