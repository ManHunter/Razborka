package com.razborka.dao;

import com.razborka.model.Request;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface RequestDao extends Dao<Request> {
    public List<Request> getPublicRequests();
    public List<Request> getRequestsByUserFrom(int user_id);
    public List<Request> getRequestsByUser(int user_id);
}
