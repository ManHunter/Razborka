package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.PartDao;
import com.razborka.model.Car;
import com.razborka.model.Part;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("partDao")
public class PartDaoImpl extends AbstractDao<Part> implements PartDao {
    @Transactional
    @Override
    public List<Part> getAllUserPart(int user_id) {
        DetachedCriteria subquery = DetachedCriteria.forClass(Car.class)
                .add(Restrictions.eq("user.id", user_id))
                .setProjection(Projections.property("id"));

        Criteria criteria = getSession().createCriteria(Part.class)
                .add(Subqueries.propertyIn("car.id", subquery));

        return criteria.list();
    }

    @Override
    public List<Part> getAllPartsCar(int car_id) {
        Criteria criteria = getSession().createCriteria(Part.class)
                .add(Restrictions.eq("car.id", car_id));
        return criteria.list();
    }
}
