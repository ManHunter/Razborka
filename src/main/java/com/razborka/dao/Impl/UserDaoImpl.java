package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.UserDao;
import com.razborka.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Transactional
    @Override
    public User getUserByName(String email) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.setMaxResults(1);
        User user = (User) criteria.uniqueResult();

        return user;
    }
}
