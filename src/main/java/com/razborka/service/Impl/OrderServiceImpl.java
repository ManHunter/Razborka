package com.razborka.service.Impl;

import com.razborka.dao.OrderDao;
import com.razborka.model.Order;
import com.razborka.service.OrderService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void saveOrder(Order order) {
        orderDao.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderDao.deleteById(id);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAll();
    }

    @Override
    public Order getOrderById(int id) {
        return orderDao.get(id);
    }
}
