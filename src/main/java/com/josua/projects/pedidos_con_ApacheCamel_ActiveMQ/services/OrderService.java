package com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.services;

import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDetailsDto;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.dto.OrderDto;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Customer;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Order;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.OrderDetails;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.entities.Product;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.exceptions.ResourceNotFoundException;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories.ICustomerRepository;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories.IOrderRepository;
import com.josua.projects.pedidos_con_ApacheCamel_ActiveMQ.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;
    @Override
    @Transactional
    public void processOrder(OrderDto orderDto) {
        System.out.println("Procesando orden: " + orderDto);
        if(orderDto.getIdCustomer() == null){
            throw new RuntimeException("El ID del cliente es obligatiorios");
        }
        Customer customer = customerRepository.findById(orderDto.getIdCustomer())
                .orElseThrow(() -> new ResourceNotFoundException("No hay ningun cliente con el id: " + orderDto.getIdCustomer()));

        Set<Long> productsIds = orderDto.getOrderDetailsDtoList().stream()
                .map(OrderDetailsDto::getId)
                .collect(Collectors.toSet());

        Map<Long, Product> productMap = productRepository.findAllById(productsIds).stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        Order order = Order.builder()
                .customer(customer)
                .orderDate(LocalDateTime.now())
                .build();

        List<OrderDetails> orderDetailsList = orderDto.getOrderDetailsDtoList().stream()
                        .map(orderDetails -> OrderDetails.builder()
                                .order(order)
                                .product(productMap.get(orderDetails.getId()))
                                .quantity(orderDetails.getQuantity())
                                .build())
                        .toList();

        order.setOrderDetailsList(orderDetailsList);

        orderRepository.save(order);

        System.out.println("Orden guardada en la base de datos con ID: " + order.getId());
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado la orden con el id: " + id));
        orderRepository.deleteById(id);
    }


}
