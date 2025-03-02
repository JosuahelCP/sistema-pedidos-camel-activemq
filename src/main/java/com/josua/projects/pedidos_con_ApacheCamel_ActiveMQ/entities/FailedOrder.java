package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "failed_orders")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FailedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String orderDetails;
    private String errorMessage;
    private LocalDateTime failedAt;
}
