package com.razborka.dao;

import com.razborka.model.Photo;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PhotoDao extends Dao<Photo> {
    public Photo getPhotoByImageName(String imageName);
    public List<Photo> getAllPhotoByPartId(int part_id);
}
