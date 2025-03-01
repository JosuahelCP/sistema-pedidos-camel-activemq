package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
