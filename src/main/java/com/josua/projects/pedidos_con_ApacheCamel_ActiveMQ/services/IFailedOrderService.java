package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDto;

public interface IFailedOrderService {
    void handleFailedOrder(OrderDto orderDto, String errorMessage);
}
