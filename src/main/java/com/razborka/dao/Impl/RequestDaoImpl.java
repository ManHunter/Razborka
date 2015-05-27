package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.RequestDao;
import com.razborka.model.Request;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("orderDao")
public class RequestDaoImpl extends AbstractDao<Request> implements RequestDao {

    @Override
    public List<Request> getPublicRequests() {
        return getSession().createCriteria(Request.class)
                .add(Restrictions.isNull("user"))
                .list();
    }

    @Override
    public List<Request> getRequestsByUserFrom(int user_id) {
        return getSession().createCriteria(Request.class)
                .add(Restrictions.eq("user_from.id", user_id))
                .list();
    }

    @Override
    public List<Request> getRequestsByUser(int user_id) {
        return getSession().createCriteria(Request.class)
                .add(Restrictions.eq("user.id", user_id))
                .list();
    }
}
