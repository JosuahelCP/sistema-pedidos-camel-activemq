package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.FailedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFailedOrderRepository extends JpaRepository<FailedOrder,Long> {
}
