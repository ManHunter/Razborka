package com.razborka.service.Impl;

import com.razborka.dao.BrandDao;
import com.razborka.model.Brand;
import com.razborka.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public void saveBrand(Brand brand) {
        brandDao.save(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandDao.update(brand);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandDao.getAll();
    }

    @Override
    public void deleteBrand(int id) {
        brandDao.deleteById(id);
    }

    @Override
    public Brand getBrandById(int id) {
        return brandDao.get(id);
    }
}
