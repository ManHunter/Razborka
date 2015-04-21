package com.razborka.service;

import com.razborka.model.Kpp;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface KppService {

    public void saveKpp(Kpp kpp);

    public void updateKpp(Kpp kpp);

    public void deleteKpp(int id);

    public List<Kpp> getAllKpp();

    public Kpp getKppById(int id);
}
