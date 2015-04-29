package com.razborka.service.Impl;

import com.razborka.dao.PartDao;
import com.razborka.model.Part;
import com.razborka.model.Photo;
import com.razborka.service.PartService;
import com.razborka.service.PhotoService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Service
@Transactional
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    @Autowired
    private PhotoService photoService;

    public void savePart(Part part) {
        part.setDate(LocalDateTime.now());
        partDao.save(part);
    }

    public void updatePart(Part part) {
        partDao.update(part);
    }

    public void deletePart(int id) {
        partDao.deleteById(id);
    }

    public List<Part> getAllPart() {
        return partDao.getAll();
    }

    public Part getPartById(int id) {
        return partDao.get(id);
    }

    public List<Part> getAllUserPart(int user_id) {
        return partDao.getAllUserPart(user_id);
    }

    public List<Part> getAllPartsCar(int car_id) {
        return partDao.getAllPartsCar(car_id);
    }


}
