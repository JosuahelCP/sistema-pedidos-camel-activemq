package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
