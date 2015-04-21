package com.razborka.service;

import com.razborka.model.User;

import java.util.List;

/**
 * Created by Admin on 08.04.2015.
 */
public interface UserService {

    public void saveUser(User user);

    public void updateUser(User user);

    public List<User> getAllUsers();

    public void deleteUser(int id);

    public User getUserByName(String email);

    public User getUserById(int id);

    public User getCurrentUser();
}
