package org.uv.SGLAC.services;

import org.uv.SGLAC.dtos.OrderRequestDTO;
import org.uv.SGLAC.dtos.OrderResponseDTO;
import org.uv.SGLAC.entities.Order;

import java.util.List;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO req); //No cambiar!

    List<Order> getAllOrders();

    OrderResponseDTO getOrderById(Long id); //No cambiar!

    Order updateOrder(Order order);

    void deleteOrder(Long id);
}
