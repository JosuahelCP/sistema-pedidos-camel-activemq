package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
