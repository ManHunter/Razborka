package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.FuelDao;
import com.razborka.model.Fuel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 11.04.2015.
 */
@Repository("fuelDao")
public class FuelDaoImpl extends AbstractDao<Fuel> implements FuelDao {

}
