package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.Order;
import org.uv.SGLAC.services.OrderService;

import org.uv.SGLAC.dtos.OrderRequestDTO;
import org.uv.SGLAC.dtos.OrderResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponseDTO create(@RequestBody OrderRequestDTO orderDTO) { //No cambiar!
        return orderService.create(orderDTO);
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {//No cambiar!
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
