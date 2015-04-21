package com.razborka.service;

import com.razborka.model.Body;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface BodyTypeService {

    public void saveBody(Body body);

    public void updateBody(Body body);

    public void deleteBody(int id);

    public List<Body> getAllBody();

    public Body getBodyById(int id);

}
