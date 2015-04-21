package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.CarDao;
import com.razborka.model.Body;
import com.razborka.model.Car;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("carDao")
public class CarDaoImpl extends AbstractDao<Car> implements CarDao {

}
