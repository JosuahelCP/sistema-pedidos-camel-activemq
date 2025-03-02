package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Customer;

public interface ICustomerService {

    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
}
