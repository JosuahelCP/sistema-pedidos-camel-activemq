package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDto;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Order;

import java.util.List;

public interface IOrderService {
    void processOrder(OrderDto order);
    List<Order> getAllOrders();

    void deleteOrder(Long id);
}
