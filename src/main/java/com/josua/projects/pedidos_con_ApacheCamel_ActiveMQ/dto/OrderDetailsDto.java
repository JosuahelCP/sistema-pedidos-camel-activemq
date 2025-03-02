package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OrderDetailsDto implements Serializable {
    private Long id;
    private Integer quantity;
}
