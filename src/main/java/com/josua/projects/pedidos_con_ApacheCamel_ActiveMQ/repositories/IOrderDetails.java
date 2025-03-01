package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetails extends JpaRepository<OrderDetails, Long> {
}
