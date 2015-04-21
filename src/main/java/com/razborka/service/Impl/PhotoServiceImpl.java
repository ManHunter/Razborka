package com.razborka.service.Impl;

import com.razborka.dao.PhotoDao;
import com.razborka.model.Photo;
import com.razborka.service.PhotoService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    public void savePhoto(Photo photo) {
        photoDao.save(photo);
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoDao.update(photo);
    }

    @Override
    public void deletePhoto(int id) {
        photoDao.deleteById(id);
    }

    @Override
    public List<Photo> getAllPhoto() {
        return photoDao.getAll();
    }

    @Override
    public Photo getPhotoById(int id) {
        return photoDao.get(id);
    }
}
