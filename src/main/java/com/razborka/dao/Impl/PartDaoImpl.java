package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.PartDao;
import com.razborka.model.Body;
import com.razborka.model.Part;
import com.razborka.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
        Criteria criteria = getSession().createCriteria(Part.class);
        criteria.add(Restrictions.eq("user.id", user_id));
        return criteria.list();
    }
}
