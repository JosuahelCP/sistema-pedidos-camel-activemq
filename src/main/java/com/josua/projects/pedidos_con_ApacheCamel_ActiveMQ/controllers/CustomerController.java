package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.controllers;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Customer;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/customers")

public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(Collections.singletonMap("message","Se ha eliminado correctamente"));
    }
}
