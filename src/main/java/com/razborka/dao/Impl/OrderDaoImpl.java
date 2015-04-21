package com.razborka.dao.Impl;

import com.razborka.dao.AbstractDao;
import com.razborka.dao.OrderDao;
import com.razborka.model.Body;
import com.razborka.model.Order;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

}
