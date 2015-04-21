package com.razborka.service;

import com.razborka.model.PartType;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PartTypeService {

    public void savePartType(PartType part);

    public void updatePartType(PartType part);

    public void deletePartType(int id);

    public List<PartType> getAllPartType();

    public PartType getPartTypeById(int id);

    public List<PartType> getPartTypeByGroupId(int groupId);
}
