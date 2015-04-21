package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.AddressDao;
import com.razborka.model.Address;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 20.04.2015.
 */
@Repository("addressDao")
public class AddressDaoImpl extends AbstractDao<Address> implements AddressDao {
    @Override
    public List<Address> getAddressByUserId(int user_id) {
        Criteria criteria = getSession().createCriteria(Address.class)
                .add(Restrictions.eq("user.id", user_id));
        return criteria.list();
    }
}
