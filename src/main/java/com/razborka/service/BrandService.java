package com.razborka.service;

import com.razborka.model.Brand;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface BrandService {

    public void saveBrand(Brand brand);

    public void updateBrand(Brand brand);

    public List<Brand> getAllBrand();

    public void deleteBrand(int id);

    public Brand getBrandById(int id);

}
