package com.razborka.dao;

import com.razborka.model.PartType;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PartTypeDao extends Dao<PartType> {
    public List<PartType> getPartTypeByGroupId(int groupId);
}
