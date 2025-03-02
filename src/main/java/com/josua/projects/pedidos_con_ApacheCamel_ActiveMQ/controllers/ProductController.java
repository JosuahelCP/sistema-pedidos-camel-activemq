package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.controllers;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Product;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(Collections.singletonMap("message:","Se ha eliminado correctamente"));
    }
}
