package com.razborka.service;

import com.razborka.model.Order;

import java.util.List;

/**
 * Created by Admin on 14.04.2015.
 */
public interface OrderService {

    public void saveOrder(Order order);

    public void updateOrder(Order order);

    public void deleteOrder(int id);

    public List<Order> getAllOrder();

    public Order getOrderById(int id);

}
