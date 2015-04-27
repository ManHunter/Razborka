package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.CarDao;
import com.razborka.model.Body;
import com.razborka.model.Car;
import com.razborka.model.Part;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("carDao")
public class CarDaoImpl extends AbstractDao<Car> implements CarDao {

    @Override
    public int countPartByCarId(int car_id) {
        int count = ((Number) getSession().createCriteria(Part.class)
                .add(Restrictions.eq("car.id", car_id))
                .setProjection(Projections.rowCount())
                .uniqueResult()).intValue();

        return count;
    }

    @Override
    public List<Car> getAllUserCars(int user_id) {
        Criteria criteria = getSession().createCriteria(Car.class)
                .add(Restrictions.eq("user.id", user_id));
        return criteria.list();
    }
}
