package com.razborka.service.Impl;

import com.razborka.dao.ServiceDao;
import com.razborka.model.Service;
import com.razborka.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 18.04.2015.
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Override
    public void saveService(Service service) {
        serviceDao.save(service);
    }

    @Override
    public void updateService(Service service) {
        serviceDao.save(service);
    }

    @Override
    public void deleteService(int id) {
        serviceDao.deleteById(id);
    }

    @Override
    public List<Service> getAllService() {
        return serviceDao.getAll();
    }

    @Override
    public Service getServiceById(int id) {
        return serviceDao.get(id);
    }
}
