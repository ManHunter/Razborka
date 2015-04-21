package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.KppDao;
import com.razborka.model.Body;
import com.razborka.model.Kpp;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("kppDao")
public class KppDaoImpl extends AbstractDao<Kpp> implements KppDao {

}
