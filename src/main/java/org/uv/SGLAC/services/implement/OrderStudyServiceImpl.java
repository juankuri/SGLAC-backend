package org.uv.SGLAC.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.entities.OrderStudy;
import org.uv.SGLAC.entities.Order;
import org.uv.SGLAC.entities.Study;
import org.uv.SGLAC.entities.OrderStatus;
import org.uv.SGLAC.repositories.OrderStudyRepository;
import org.uv.SGLAC.repositories.OrderRepository;
import org.uv.SGLAC.repositories.StudyRepository;
import org.uv.SGLAC.services.OrderStudyService;
import java.util.List;

@Service
public class OrderStudyServiceImpl implements OrderStudyService {

    @Autowired
    private OrderStudyRepository orderStudyRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Override
    public OrderStudy saveOrderStudy(OrderStudy orderStudy) {
        // Validaciones
        Order order = orderRepository.findById(orderStudy.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("La orden no existe"));

        Study study = studyRepository.findById(orderStudy.getStudy().getId())
                .orElseThrow(() -> new RuntimeException("El estudio no existe"));

        // Inicializar estado si no viene
        if (orderStudy.getStatus() == null) {
            orderStudy.setStatus(OrderStatus.CREATED);
        }

        orderStudy.setOrder(order);
        orderStudy.setStudy(study);

        return orderStudyRepository.save(orderStudy);
    }

    @Override
    public List<OrderStudy> getAllOrderStudies() {
        return orderStudyRepository.findAll();
    }

    @Override
    public OrderStudy getOrderStudyById(Long id) {
        return orderStudyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderStudy no encontrado"));
    }

    @Override
    public OrderStudy updateOrderStudy(Long id, OrderStudy updatedOrderStudy) {
        OrderStudy existing = orderStudyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderStudy no encontrado"));

        // Solo se puede actualizar estado y notas
        if (updatedOrderStudy.getStatus() != null) {
            existing.setStatus(updatedOrderStudy.getStatus());
        }
        if (updatedOrderStudy.getNotes() != null) {
            existing.setNotes(updatedOrderStudy.getNotes());
        }

        return orderStudyRepository.save(existing);
    }

    @Override
    public void deleteOrderStudy(Long id) {
        OrderStudy existing = orderStudyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderStudy no encontrado"));

        // Validación: no eliminar estudios que estén en proceso o completados
        if (existing.getStatus() != OrderStatus.CREATED) {
            throw new RuntimeException("No se puede eliminar un estudio que ya está en progreso o completado");
        }

        orderStudyRepository.delete(existing);
    }
}
