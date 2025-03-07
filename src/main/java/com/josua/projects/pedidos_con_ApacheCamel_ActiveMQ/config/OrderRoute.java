package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.config;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDto;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Order;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services.IFailedOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

// Creamos la ruta y extiende de la clase RouteBuilder
@Component
@RequiredArgsConstructor
public class OrderRoute extends RouteBuilder {
    private final IFailedOrderService failedOrderService;
    @Override
    public void configure() throws Exception {
        //Manejo de errores con reintentos y DLQ
        //DLQ es una cola donde se envian los mensajes que fallaron despues de varios intentos
        onException(Exception.class)
                .maximumRedeliveries(3) // Intentara porcesar el mensaje 3 veces
                .redeliveryDelay(5000)// Espera 5s entre cada intento
                .logRetryAttempted(true)
                         // Indica que el error ya está manejado y que no debe propagar el error a otras partes de la aplicación
                .to("jms:queue:order.dead")// Si despues de 3 intentos sigue fallando se manda a order.dead
                .log("Error procesando orden: ${exception.message}");
                //

        from("jms:queue:order.queue") // Escucha la cola en ActiveMQ order.queue
                .log("Mensaje recibido: ${body}")
                .to("bean:orderService?method=processOrder"); // Envia al servicio para procesar la orden

        from("jms:queue:order.dead")
                .log("Pedido movido a la DLQ: ${body}")
                .process(exchange -> {
                    OrderDto orderDto = exchange.getIn().getBody(OrderDto.class);
                    String errorMessage = "Error despues de 3 intentos";
                    failedOrderService.handleFailedOrder(orderDto, errorMessage);
                });
    }
}
