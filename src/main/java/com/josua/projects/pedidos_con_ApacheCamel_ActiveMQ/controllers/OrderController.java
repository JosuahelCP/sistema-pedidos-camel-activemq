package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.controllers;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDto;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Order;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services.IOrderService;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services.activeMQ.OrderProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducerService producerService;
    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        try {
            if(orderDto.getIdCustomer() == null || orderDto.getOrderDetailsDtoList().isEmpty()){
                throw new IllegalArgumentException("El id del cliente y los detalles de orden son requeridos");
            }
            producerService.sendOrder(orderDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Pedido recibido y esta siendo procesado...");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Informacion de orden invalida: " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error procesando la orden: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> allOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
