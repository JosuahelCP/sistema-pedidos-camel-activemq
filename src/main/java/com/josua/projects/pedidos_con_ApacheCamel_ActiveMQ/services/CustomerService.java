package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Customer;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{

    private final ICustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
