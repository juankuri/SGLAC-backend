package org.uv.SGLAC.services.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uv.SGLAC.dtos.OrderRequestDTO;
import org.uv.SGLAC.dtos.OrderResponseDTO;
import org.uv.SGLAC.entities.Order;
import org.uv.SGLAC.entities.OrderStatus;
import org.uv.SGLAC.repositories.OrderRepository;
import org.uv.SGLAC.services.OrderService;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    //Nota: hay errores en el codigo debido a la implementacion del OrderDTO en el OrderService. Usar OrderDTO, en vez de Order.

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO orderDTO) {
        
    // ===================================
    // TODO:
    // 1. Usar OrderRequestDTO como entrada del metodo
    // 2. Validar que la lista de estudios no sea nula ni vacia y que cada Study
    //    exista en la base de datos antes de crear la orden.
    // 3. Inicializar el estado de la orden y de cada OrderStudy en CREATED.
    // 4. Realizar el cambio de DTO dentro del service
    //    (OrderRequestDTO > Order, Order > OrderResponseDTO),
    //    usando los DTOs agregados:
    //    - OrderStudyRequestDTO
    //    - OrderStudyResponseDTO
    // 5. Guardar la orden en db y retornar un OrderResponseDTO.
    // ===================================
        
        // if (orderDTO.getPatient() == null) {
        //     throw new RuntimeException("La orden debe tener un paciente asignado.");
        // }
        // if (orderDTO.getLabTechnician() == null) {
        //     throw new RuntimeException("La orden debe tener un técnico de laboratorio asignado.");
        // }
        // if (order.getStatus() == null) {
        //     order.setStatus(OrderStatus.CREATED);
        // }
        // order.updateRequestDateTimeToNow();
        
        //Nota: Hay que convertir el RequestDTO a un ResponseDTO.
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        //TODO; Implementar metodo. 
        throw new UnsupportedOperationException("Unimplemented method 'getOrderById");    
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
