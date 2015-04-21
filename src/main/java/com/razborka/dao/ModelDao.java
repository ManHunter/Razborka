package com.razborka.dao;

import com.razborka.model.Model;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface ModelDao extends Dao<Model> {
    public List<Model> getModelByBrandId(int brand_id);
}
