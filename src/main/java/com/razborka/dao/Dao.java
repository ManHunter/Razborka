package com.razborka.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
public interface Dao<T extends Object> {
    public void save(T t);
    public void update(T t);
    public void deleteById(Serializable id);
    public T get(Serializable id);
    public List<T> getAll();
    public long count();
}
