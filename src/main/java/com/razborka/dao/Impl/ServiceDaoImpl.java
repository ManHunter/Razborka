package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.ServiceDao;
import com.razborka.model.Service;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 18.04.2015.
 */
@Repository("serviceDao")
public class ServiceDaoImpl extends AbstractDao<Service> implements ServiceDao {

}
