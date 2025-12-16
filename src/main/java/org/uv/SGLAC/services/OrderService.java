package org.uv.SGLAC.services;

import org.uv.SGLAC.dtos.OrderRequestDTO;
import org.uv.SGLAC.dtos.OrderResponseDTO;
import org.uv.SGLAC.entities.Order;
import org.uv.SGLAC.entities.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO req); // No cambiar

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO getOrderById(Long id); // No cambiar

    Order updateOrder(Order order);

    OrderResponseDTO updateStatus(Long id, OrderStatus status);

    void deleteOrder(Long id);
}
