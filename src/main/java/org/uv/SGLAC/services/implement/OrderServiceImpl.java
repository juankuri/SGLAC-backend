package org.uv.SGLAC.services.implement;

import org.springframework.security.core.context.ObservationSecurityContextChangedListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uv.SGLAC.dtos.LabTechnicianDTO;
import org.uv.SGLAC.dtos.OrderRequestDTO;
import org.uv.SGLAC.dtos.OrderResponseDTO;
import org.uv.SGLAC.dtos.OrderStudyRequestDTO;
import org.uv.SGLAC.dtos.OrderStudyResponseDTO;
import org.uv.SGLAC.dtos.PatientDTO;
import org.uv.SGLAC.dtos.StudyDTO;
import org.uv.SGLAC.entities.LabTechnician;
import org.uv.SGLAC.entities.Order;
import org.uv.SGLAC.entities.OrderStatus;
import org.uv.SGLAC.entities.OrderStudy;
import org.uv.SGLAC.entities.Patient;
import org.uv.SGLAC.entities.Study;
import org.uv.SGLAC.repositories.LabTechnicianRepository;
import org.uv.SGLAC.repositories.OrderRepository;
import org.uv.SGLAC.repositories.PatientRepository;
import org.uv.SGLAC.repositories.StudyRepository;
import org.uv.SGLAC.services.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final StudyRepository studyRepository;
    private final PatientRepository patientRepository;
    private final LabTechnicianRepository labTechnicianRepository;

    public OrderServiceImpl(OrderRepository orderRepository, StudyRepository studyRepository,
            PatientRepository patientRepository,
            LabTechnicianRepository labTechnicianRepository) {
        this.orderRepository = orderRepository;
        this.studyRepository = studyRepository;
        this.patientRepository = patientRepository;
        this.labTechnicianRepository = labTechnicianRepository;
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO orderDTO) {

        // ===================================
        // TODO:
        // 1. Usar OrderRequestDTO como entrada del metodo
        // 2. Validar que la lista de estudios no sea nula ni vacia y que cada Study
        // exista en la base de datos antes de crear la orden.
        // 3. Inicializar el estado de la orden y de cada OrderStudy en CREATED.
        // 4. Realizar el cambio de DTO dentro del service
        // (OrderRequestDTO > Order, Order > OrderResponseDTO),
        // usando los DTOs agregados:
        // - OrderStudyRequestDTO
        // - OrderStudyResponseDTO
        // 5. Guardar la orden en db y retornar un OrderResponseDTO.
        // ===================================

        if (orderDTO.getPatientId() == null) {
            throw new RuntimeException("La orden debe tener un paciente asignado.");
        }

        if (orderDTO.getLabTechnicianId() == null) {
            throw new RuntimeException("La orden debe tener un técnico asignado.");
        }

        if (orderDTO.getStudies() == null || orderDTO.getStudies().isEmpty()) {
            throw new RuntimeException("La orden debe contener al menos un estudio.");
        }
        Patient patient = patientRepository.findById(orderDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        LabTechnician technician = labTechnicianRepository.findById(orderDTO.getLabTechnicianId())
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        Order order = new Order();
        order.setPatient(patient);
        order.setLabTechnician(technician);
        order.setNotes(orderDTO.getNotes());
        order.setStatus(OrderStatus.CREATED);
        order.updateRequestDateTimeToNow();
        order.setOrderStudies(new ArrayList<>());
        
        for (OrderStudyRequestDTO s : orderDTO.getStudies()) {

            Study study = studyRepository.findById(s.getStudyId())
                    .orElseThrow(() -> new RuntimeException("Estudio no encontrado con ID: " + s.getStudyId()));

            OrderStudy os = new OrderStudy();
            os.setOrder(order);
            os.setStudy(study);
            os.setStatus(OrderStatus.CREATED);

            order.addOrderStudy(os);

        }

        Order savedOrder = orderRepository.save(order);

        // Entity a ResponseDTO

        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(savedOrder.getId());
        response.setStatus(savedOrder.getStatus());
        response.setNotes(savedOrder.getNotes());
        response.setRequestDateTime(savedOrder.getRequestDateTime());

        // Para el paciente
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(savedOrder.getPatient().getId());
        patientDTO.setFullName(savedOrder.getPatient().getUser().getNames());
        response.setPatient(patientDTO);

        // Para el laboratodista
        LabTechnicianDTO technicianDTO = new LabTechnicianDTO();
        technicianDTO.setId(savedOrder.getLabTechnician().getId());
        technicianDTO.setFullName(savedOrder.getLabTechnician().getUser().getNames());
        response.setLabTechnician(technicianDTO);

        // Para los estudios
        response.setStudies(
                savedOrder.getOrderStudies().stream().map(os -> {

                    OrderStudyResponseDTO s = new OrderStudyResponseDTO();
                    s.setId(os.getId());
                    s.setStatus(os.getStatus());
                    s.setNotes(os.getNotes());

                    StudyDTO studyDTO = new StudyDTO();
                    studyDTO.setId(os.getStudy().getId());
                    studyDTO.setName(os.getStudy().getName()); // si existe

                    s.setStudy(studyDTO);

                    return s;
                }).toList());

        return response;
    }
    // Nota: Hay que convertir el RequestDTO a un ResponseDTO.

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));

        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        response.setRequestDateTime(order.getRequestDateTime());
        response.setStatus(order.getStatus());
        response.setNotes(order.getNotes());

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(order.getPatient().getId());
        patientDTO.setFullName(order.getPatient().getUser().getNames()); // ajusta a tus campos
        response.setPatient(patientDTO);

        LabTechnicianDTO technicianDTO = new LabTechnicianDTO();
        technicianDTO.setId(order.getLabTechnician().getId());
        technicianDTO.setFullName(order.getLabTechnician().getUser().getNames()); // ajusta
        response.setLabTechnician(technicianDTO);

        response.setStudies(
                order.getOrderStudies().stream().map(os -> {

                    OrderStudyResponseDTO s = new OrderStudyResponseDTO();
                    s.setId(os.getId());
                    s.setStatus(os.getStatus());
                    s.setNotes(os.getNotes());

                    StudyDTO studyDTO = new StudyDTO();
                    studyDTO.setId(os.getStudy().getId());
                    studyDTO.setName(os.getStudy().getName()); // opcional
                    s.setStudy(studyDTO);

                    return s;
                }).toList());

        return response;
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
            throw new RuntimeException(
                    "No se puede modificar paciente o técnico si la orden tiene estudios procesados.");
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
