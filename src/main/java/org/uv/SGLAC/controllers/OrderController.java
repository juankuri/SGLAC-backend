package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.entities.Order;
import org.uv.SGLAC.entities.OrderStatus;
import org.uv.SGLAC.services.OrderService;

import org.uv.SGLAC.dtos.OrderRequestDTO;
import org.uv.SGLAC.dtos.OrderResponseDTO;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponseDTO create(@RequestBody OrderRequestDTO orderDTO) {
        return orderService.create(orderDTO);
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @PutMapping("/status/{id}")
    public OrderResponseDTO updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, OrderStatus> body) {

        return orderService.updateStatus(id, body.get("status"));
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
