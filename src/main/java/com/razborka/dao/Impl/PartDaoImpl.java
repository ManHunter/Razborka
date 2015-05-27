package com.razborka.dao.Impl;

import com.razborka.Constants;
import com.razborka.dao.AbstractDao;
import com.razborka.dao.PartDao;
import com.razborka.model.Address;
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
                .add(Subqueries.propertyIn("car.id", subquery))
                .addOrder(Order.desc("date"));

        return criteria.list();
    }

    @Override
    public List<Part> getAllPartsCar(int car_id) {
        Criteria criteria = getSession().createCriteria(Part.class)
                .add(Restrictions.eq("car.id", car_id))
                .addOrder(Order.desc("date"));
        return criteria.list();
    }

    @Override
    public List<Part> getUserPartsByCar(int user_id, int car_id, int page, boolean isAll) {
        DetachedCriteria subquery = DetachedCriteria.forClass(Car.class)
                .add(Restrictions.eq("user.id", user_id))
                .add(Restrictions.eq("id", car_id))
                .setProjection(Projections.property("id"));

        Criteria criteria = getSession().createCriteria(Part.class)
                .add(Subqueries.propertyIn("car.id", subquery));

        if (isAll == false) {
            criteria.setFirstResult(page * Constants.PART_PAGES);
            criteria.setMaxResults(Constants.PART_PAGES);
        }

        return criteria.list();
    }

    @Override
    public List<Part> getPartsByOtherSeller(int user_id, Car car) {
        DetachedCriteria subquery = DetachedCriteria.forClass(Car.class)
                .add(Restrictions.ne("user.id", user_id))
                .add(Restrictions.eq("brand", car.getBrand()))
                .add(Restrictions.eq("model", car.getModel()))
                .setProjection(Projections.property("id"));

        Criteria criteria = getSession().createCriteria(Part.class)
                .add(Subqueries.propertyIn("car.id", subquery))
                .addOrder(Order.desc("date"));
        return criteria.list();
    }

    @Override
    public List<Part> partFilter(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id,
                                 int body_id, int part_group_id, int part_type_id, String city, int page,
                                 boolean isAll) {
        DetachedCriteria subquery = DetachedCriteria.forClass(Address.class)
                .add(Restrictions.eq("city", city))
                .setProjection(Projections.property("user.id"));

        Criteria criteria = getSession().createCriteria(Part.class)
                .createAlias("car", "c")
                .addOrder(Order.desc("date"));

        if (user_id != 0)
            criteria.add(Restrictions.eq("c.user.id", user_id));
        if (brand_id != 0)
            criteria.add(Restrictions.eq("c.brand.id", brand_id));
        if (model_id != 0)
            criteria.add(Restrictions.eq("c.model.id", model_id));
        if (year != 0)
            criteria.add(Restrictions.ge("c.year_to", year));
        if (year != 0)
            criteria.add(Restrictions.le("c.year_from", year));
        if (fuel_id != 0)
            criteria.add(Restrictions.eq("c.fuel.id", fuel_id));
        if (volume != 0)
            criteria.add(Restrictions.eq("c.volume", volume));
        if (body_id != 0)
            criteria.add(Restrictions.eq("c.body.id", body_id));
        if (part_group_id != 0)
            criteria.add(Restrictions.eq("group.id", part_group_id));
        if (part_type_id != 0)
            criteria.add(Restrictions.eq("type.id", part_type_id));
        if (!city.equals("0")) {
            //criteria.add(Restrictions.eq("city", city));
            criteria.add(Subqueries.propertyIn("c.user.id", subquery));
        }
        if (isAll == false) {
            criteria.setFirstResult(page * Constants.PART_PAGES);
            criteria.setMaxResults(Constants.PART_PAGES);
        }

        return criteria.list();
    }

    @Override
    public int numberOfParts(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id,
                             int body_id, int part_group_id, int part_type_id, String city) {
        return partFilter(user_id, brand_id, model_id, year, volume, fuel_id,
                body_id, part_group_id, part_type_id, city, 0, true)
                .size();
    }

    @Override
    public int numberOfParts(int user_id, int car_id) {
        return getUserPartsByCar(user_id, car_id, 0, true)
                .size();
    }


}
