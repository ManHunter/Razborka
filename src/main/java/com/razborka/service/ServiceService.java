package com.razborka.service;

import com.razborka.dao.Dao;
import com.razborka.model.Service;

import java.util.List;

/**
 * Created by Admin on 18.04.2015.
 */
public interface ServiceService {

    public void saveService(Service service);

    public void updateService(Service service);

    public void deleteService(int id);

    public List<Service> getAllService();

    public Service getServiceById(int id);

    public List<Service> getAllUserServices(int user_id);
}
