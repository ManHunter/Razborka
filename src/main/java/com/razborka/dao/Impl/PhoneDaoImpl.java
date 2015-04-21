package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.PhoneDao;
import com.razborka.model.Phone;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
@Repository("phoneDao")
public class PhoneDaoImpl extends AbstractDao<Phone> implements PhoneDao {
    @Override
    public List<Phone> getPhoneByUserId(int user_id) {
        Criteria criteria = getSession().createCriteria(Phone.class)
                .add(Restrictions.eq("user.id", user_id));
        return criteria.list();
    }
}
