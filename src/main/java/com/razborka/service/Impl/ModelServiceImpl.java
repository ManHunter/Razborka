package com.razborka.service.Impl;

import com.razborka.dao.ModelDao;
import com.razborka.model.Model;
import com.razborka.service.ModelService;
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
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelDao modelDao;

    @Override
    public void saveModel(Model model) {
        modelDao.save(model);
    }

    @Override
    public void updateModel(Model model) {
        modelDao.update(model);
    }

    @Override
    public void deleteModel(int id) {
        modelDao.deleteById(id);
    }

    @Override
    public List<Model> getAllModel() {
        return modelDao.getAll();
    }

    @Override
    public Model getModelById(int id) {
        return modelDao.get(id);
    }

    @Override
    public List<Model> getModelByBrandId(int brand_id) {
        return modelDao.getModelByBrandId(brand_id);
    }
}
