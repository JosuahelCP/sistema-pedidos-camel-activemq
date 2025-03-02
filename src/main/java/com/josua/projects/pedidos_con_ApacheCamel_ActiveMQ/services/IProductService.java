package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Product;

public interface IProductService {
    Product saveProduct(Product product);
    void deleteProduct(Long id);
}
