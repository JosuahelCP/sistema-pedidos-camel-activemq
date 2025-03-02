package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class OrderDto implements Serializable {

    private Long idCustomer;
    List<OrderDetailsDto> orderDetailsDtoList;
}
