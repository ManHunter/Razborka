package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.RepairTypeDao;
import com.razborka.model.RepairType;
import com.razborka.model.Service;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("repairTypeDao")
public class RepairTypeDaoImpl extends AbstractDao<RepairType> implements RepairTypeDao {

    @Override
    public List<RepairType> getAllRepairTypeExceptUser(int user_id) {
        Query query = getSession().createQuery("from RepairType as rt where not exists " +
                "(select s.type.id from Service as s where rt.id=s.type.id and s.user.id= :user_id)")
                .setParameter("user_id", user_id);
        return query.list();
    }
}
