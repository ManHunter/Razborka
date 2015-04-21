package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.ModelDao;
import com.razborka.model.Body;
import com.razborka.model.Model;
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
@Repository("modelDao")
public class ModelDaoImpl extends AbstractDao<Model> implements ModelDao {

    @Transactional
    @Override
    public List<Model> getModelByBrandId(int brand_id) {
        Criteria criteria = getSession().createCriteria(Model.class)
                .add(Restrictions.eq("brand.id", brand_id));
        return criteria.list();
    }
}
