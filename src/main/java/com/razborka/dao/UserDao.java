package com.razborka.dao;

import com.razborka.model.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.util.List;

/**
 * Created by Admin on 08.04.2015.
 */
public interface UserDao extends Dao<User> {
    public User getUserByName(String email);
}
