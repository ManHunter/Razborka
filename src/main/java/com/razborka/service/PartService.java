package com.razborka.service;

import com.razborka.model.Part;
import com.razborka.model.User;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PartService {

    public void savePart(Part part);

    public void updatePart(Part part);

    public void deletePart(int id);

    public List<Part> getAllPart();

    public Part getPartById(int id);

    public List<Part> getAllUserPart(int user_id);
}
