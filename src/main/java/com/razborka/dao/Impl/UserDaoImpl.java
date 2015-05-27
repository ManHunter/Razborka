package com.razborka.dao.Impl;

import com.razborka.Constants;
import com.razborka.dao.AbstractDao;
import com.razborka.dao.UserDao;
import com.razborka.model.Address;
import com.razborka.model.Car;
import com.razborka.model.Part;
import com.razborka.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Transactional
    public User getUserByName(String email) {
        User user = (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .setMaxResults(1)
                .uniqueResult();
        return user;
    }

    @Transactional
    @Override
    public List<User> getAllSeller(String role) {
        return getSession().createCriteria(User.class)
                .add(Restrictions.eq("role", role))
                .list();
    }

    @Transactional
    @Override
    public List<User> getAllSeller(String role, int page) {
        return getSession().createCriteria(User.class)
                .add(Restrictions.eq("role", role))
                .setFirstResult(page * Constants.PART_PAGES)
                .setMaxResults(Constants.PART_PAGES)
                .list();
    }

    public List<User> sellerFilter(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id,
                                   int body_id, int part_group_id, int part_type_id, String city, int page,
                                   boolean isAll) {
        Criteria criteria = getSession().createCriteria(User.class)
                .add(Restrictions.eq("role", Constants.SELLER_ROLE));

        if (user_id != 0 || brand_id != 0 || model_id != 0 || year != 0 || fuel_id != 0 || volume != 0 ||
                body_id != 0 || part_group_id != 0 || part_type_id != 0 || !city.equals("0")) {

            DetachedCriteria subquery = DetachedCriteria.forClass(User.class, "u")
                    .setProjection(Projections.property("u.id"))
                    .setProjection(Projections.groupProperty("u.id"));

            if (user_id != 0 || brand_id != 0 || model_id != 0 || year != 0 || fuel_id != 0 || volume != 0 ||
                    body_id != 0 || part_group_id != 0 || part_type_id != 0) {
                subquery.createAlias("u.cars", "c");
                subquery.createAlias("c.parts", "p");

                if (user_id != 0)
                    subquery.add(Restrictions.eq("c.user.id", user_id));
                if (brand_id != 0)
                    subquery.add(Restrictions.eq("c.brand.id", brand_id));
                if (model_id != 0)
                    subquery.add(Restrictions.eq("c.model.id", model_id));
                if (year != 0)
                    subquery.add(Restrictions.ge("c.year_to", year));
                if (year != 0)
                    subquery.add(Restrictions.le("c.year_from", year));
                if (fuel_id != 0)
                    subquery.add(Restrictions.eq("c.fuel.id", fuel_id));
                if (volume != 0)
                    subquery.add(Restrictions.eq("c.volume", volume));
                if (body_id != 0)
                    subquery.add(Restrictions.eq("c.body.id", body_id));
                if (part_group_id != 0)
                    subquery.add(Restrictions.eq("p.group.id", part_group_id));
                if (part_type_id != 0)
                    subquery.add(Restrictions.eq("p.type.id", part_type_id));
            }
            if (!city.equals("0")) {
                subquery.add(Restrictions.eq("a.city", city));
                subquery.createAlias("u.addresses", "a");
            }

            criteria.add(Subqueries.propertyIn("id", subquery));
        }

        if (isAll == false) {
            criteria.setFirstResult(page * Constants.PART_PAGES);
            criteria.setMaxResults(Constants.PART_PAGES);
        }

        return criteria.list();
    }

    @Override
    public int numberOfPage(int user_id, int brand_id, int model_id, int year, int volume, int fuel_id,
                            int body_id, int part_group_id, int part_type_id, String city) {
        return sellerFilter(user_id, brand_id, model_id, year, volume, fuel_id,
                body_id, part_group_id, part_type_id, city, 0, true)
                .size();
    }

    public List<User> stoFilter(int user_id, String city, int repairType, int page, boolean isAll) {

        DetachedCriteria subquery = DetachedCriteria.forClass(User.class, "u")
                .setProjection(Projections.groupProperty("u.id"));

        if (user_id != 0) subquery.add(Restrictions.eq("u.id", user_id));

        if (!city.equals("0")) {
            subquery.createAlias("u.addresses", "address");
            subquery.add(Restrictions.eq("address.city", city));
        }
        if (repairType != 0) {
            subquery.createAlias("u.services", "service");
            subquery.add(Restrictions.eq("service.type.id", repairType));
        }

        Criteria criteria = getSession().createCriteria(User.class)
                .add(Subqueries.propertyIn("id", subquery))
                .add(Restrictions.eq("role", Constants.STO_ROLE));

        if(!isAll) {
            criteria.setFirstResult(page * Constants.PART_PAGES);
            criteria.setMaxResults(Constants.PART_PAGES);
        }

        return criteria.list();
    }

    @Override
    public int numberOfPageSto(int user, String city, int repairType) {
        return stoFilter(user, city, repairType, 0, true)
                .size();
    }
}
