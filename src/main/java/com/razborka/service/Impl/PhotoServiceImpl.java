package com.razborka.service.Impl;

import com.razborka.dao.PhotoDao;
import com.razborka.model.Part;
import com.razborka.model.Photo;
import com.razborka.service.PhotoService;
import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Service("photoService")
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    public void savePhoto(Photo photo) {
        photoDao.save(photo);
    }

    public void deletePhoto(int id) {
        photoDao.deleteById(id);
    }

    public List<Photo> getAllPhoto() {
        return photoDao.getAll();
    }

    public Photo getPhotoById(int id) {
        return photoDao.get(id);
    }

    public Photo getPhotoByImageName(String imageName) {
        return photoDao.getPhotoByImageName(imageName);
    }

    public List<Photo> getAllPhotoByPartId(int part_id) {
        return photoDao.getAllPhotoByPartId(part_id);
    }
}
