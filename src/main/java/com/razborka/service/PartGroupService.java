package com.razborka.service;

import com.razborka.model.PartGroup;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PartGroupService {

    public void savePartGroup(PartGroup part);

    public void updatePartGroup(PartGroup part);

    public void deletePartGroup(int id);

    public List<PartGroup> getAllPartGroup();

    public PartGroup getPartGroupById(int id);
}
