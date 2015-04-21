package com.razborka.service;

import com.razborka.model.Model;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface ModelService {

    public void saveModel(Model model);

    public void updateModel(Model model);

    public void deleteModel(int id);

    public List<Model> getAllModel();

    public Model getModelById(int id);

    public List<Model> getModelByBrandId(int brand_id);

}
