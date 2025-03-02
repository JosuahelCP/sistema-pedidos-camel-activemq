package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services.activeMQ;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDto;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Order;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducerService {
    private final ProducerTemplate producerTemplate;
    // Envia la orden a la cola order.queue y sera procesada por activeMQ
    //  a traves de la clase de config OrderRoute
    public void sendOrder(OrderDto orderDto){
        producerTemplate.sendBody("jms:queue:order.queue", orderDto);
    }
}
