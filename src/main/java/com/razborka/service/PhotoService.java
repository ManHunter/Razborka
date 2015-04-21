package com.razborka.service;

import com.razborka.model.Photo;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PhotoService {

    public void savePhoto(Photo part);

    public void updatePhoto(Photo part);

    public void deletePhoto(int id);

    public List<Photo> getAllPhoto();

    public Photo getPhotoById(int id);

}
