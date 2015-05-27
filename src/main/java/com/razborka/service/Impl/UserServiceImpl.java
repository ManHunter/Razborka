package com.razborka.service.Impl;

import com.razborka.Constants;
import com.razborka.dao.UserDao;
import com.razborka.model.User;
import com.razborka.service.UserService;
import com.razborka.util.EmailSender;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 08.04.2015.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        user.setDate(LocalDateTime.now());
        userDao.save(user);

        EmailSender sender = new EmailSender();
        sender.send(user, Constants.REGISTRATION);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUserByName(String email) {
        User user = userDao.getUserByName(email);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return userDao.get(id);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return getUserByName(name);
    }

    @Override
    public List<User> getAllSeller(String role) {
        return userDao.getAllSeller(role);
    }

    @Override
    public List<User> getAllSeller(String role, int page) {
        return userDao.getAllSeller(role, page);
    }

    public boolean isAvailable(String email) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if(user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public List<User> sellerFilter(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id, int body_id, int part_group_id, int part_type_id, String city, int page) {
        return userDao.sellerFilter(user_id, brand_id, model_id, year, volume, fuel_id, body_id, part_group_id, part_type_id, city, page, false);
    }

    @Override
    public int numberOfPage(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id, int body_id, int part_group_id, int part_type_id, String city) {
        return userDao.numberOfPage(user_id, brand_id, model_id, year, volume, fuel_id,
                body_id, part_group_id, part_type_id, city);
    }

    public List<User> stoFilter(int user_id, String city, int repairType, int page) {
        return userDao.stoFilter(user_id, city, repairType, page, false);
    }

    @Override
    public int numberOfPageSto(int user, String city, int repairType) {
        return userDao.numberOfPageSto(user, city, repairType);
    }
}
