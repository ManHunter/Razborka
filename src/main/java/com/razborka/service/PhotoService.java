package com.razborka.service;

import com.razborka.model.Part;
import com.razborka.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface PhotoService {

    public void savePhoto(Photo part);

    public void deletePhoto(int id);

    public List<Photo> getAllPhoto();

    public Photo getPhotoById(int id);

    public Photo getPhotoByImageName(String imageName);

    public List<Photo> getAllPhotoByPartId(int part_id);

}
