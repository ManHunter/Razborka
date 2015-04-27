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

    @Override
    public void savePhoto(Photo photo) {
        photoDao.save(photo);
    }

    @Override
    public void savePhotos(List<MultipartFile> files, Part part, String path) {
        Photo photo = new Photo();
        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String filename = generateFilename(file.getOriginalFilename());
                    File f = new File(path + "resources\\image\\part\\" + filename);
                    FileUtils.writeByteArrayToFile(f, file.getBytes());
                    photo.setPicture(filename);
                    photo.setPart(part);
                    savePhoto(photo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error save photo: " + e.getMessage());
        }
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

    public String generateFilename(String originalFilename) {
        Long nameRandom = Calendar.getInstance().getTimeInMillis();
        originalFilename = nameRandom + "_" + originalFilename;
        return originalFilename;
    }
}
