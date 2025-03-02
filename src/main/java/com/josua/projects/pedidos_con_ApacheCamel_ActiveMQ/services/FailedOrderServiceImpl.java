package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDto;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.FailedOrder;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories.IFailedOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FailedOrderServiceImpl implements IFailedOrderService{
    private final IFailedOrderRepository failedOrderRepository;
    private final ObjectMapper objectMapper;
    @Override
    public void handleFailedOrder(OrderDto orderDto, String errorMessage) {
        try {
            String orderJson = objectMapper.writeValueAsString(orderDto);
            FailedOrder failedOrder = FailedOrder.builder()
                    .orderDetails(orderJson)
                    .errorMessage(errorMessage)
                    .customerId(orderDto.getIdCustomer())
                    .failedAt(LocalDateTime.now())
                    .build();

            failedOrderRepository.save(failedOrder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
