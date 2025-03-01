package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,Long> {
}
