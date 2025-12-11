package org.uv.SGLAC.services.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uv.SGLAC.entities.Order;
import org.uv.SGLAC.entities.OrderStatus;
import org.uv.SGLAC.repositories.OrderRepository;
import org.uv.SGLAC.services.OrderService;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        if (order.getPatient() == null) {
            throw new RuntimeException("La orden debe tener un paciente asignado.");
        }
        if (order.getLabTechnician() == null) {
            throw new RuntimeException("La orden debe tener un técnico de laboratorio asignado.");
        }
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.CREATED);
        }
        order.updateRequestDateTimeToNow();
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order updateOrder(Order order) {
        if (order.getId() == null) {
            throw new RuntimeException("No se puede actualizar una orden sin ID.");
        }
        Order existingOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + order.getId()));
        boolean hasProcessedStudies = existingOrder.getOrderStudies().stream()
                .anyMatch(s -> s.getStatus() != OrderStatus.CREATED);
        if (hasProcessedStudies) {
            throw new RuntimeException("No se puede modificar paciente o técnico si la orden tiene estudios procesados.");
        }

        existingOrder.setPatient(order.getPatient());
        existingOrder.setLabTechnician(order.getLabTechnician());
        existingOrder.setNotes(order.getNotes());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));
        if (!order.getOrderStudies().isEmpty()) {
            throw new RuntimeException("No se puede eliminar una orden que tenga estudios asociados.");
        }

        orderRepository.delete(order);
    }
}
