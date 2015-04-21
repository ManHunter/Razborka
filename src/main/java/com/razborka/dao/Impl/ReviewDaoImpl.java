package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.ReviewDao;
import com.razborka.model.Review;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 19.04.2015.
 */
@Repository("reviewDao")
public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {

    @Transactional
    @Override
    public List<Review> getReviewByUserId(int user_id) {
        Criteria criteria = getSession().createCriteria(Review.class)
                .add(Restrictions.eq("user.id", user_id));
        return criteria.list();
    }
}
