package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.PartTypeDao;
import com.razborka.model.Body;
import com.razborka.model.Model;
import com.razborka.model.PartType;
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
@Repository("partTypeDao")
public class PartTypeDaoImpl extends AbstractDao<PartType> implements PartTypeDao {
    @Transactional
    @Override
    public List<PartType> getPartTypeByGroupId(int groupId) {
        Criteria criteria = getSession().createCriteria(PartType.class)
                .add(Restrictions.eq("group.id", groupId));
        return criteria.list();
    }
}
