package org.uv.SGLAC.services.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uv.SGLAC.dtos.LabTechnicianDTO;
import org.uv.SGLAC.dtos.OrderRequestDTO;
import org.uv.SGLAC.dtos.OrderResponseDTO;
import org.uv.SGLAC.dtos.OrderStudyRequestDTO;
import org.uv.SGLAC.dtos.OrderStudyResponseDTO;
import org.uv.SGLAC.dtos.ParameterDTO;
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

        for (OrderStudyRequestDTO s : orderDTO.getStudies()) {
            Study study = studyRepository.findById(s.getStudyId())
                    .orElseThrow(() -> new RuntimeException("Estudio no encontrado"));

            OrderStudy orderStudy = new OrderStudy();
            orderStudy.setStudy(study);
            orderStudy.setStatus(OrderStatus.CREATED);

            order.addOrderStudy(orderStudy);
        }

        return toDTO(orderRepository.save(order));
    }
    // Nota: Hay que convertir el RequestDTO a un ResponseDTO.

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<OrderResponseDTO> orderDTOs = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            orderDTOs.add(toDTO(order));
        }
        return orderDTOs;
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
        patientDTO.setFullName(order.getPatient().getUser().getNames()); 
        response.setPatient(patientDTO);

        LabTechnicianDTO technicianDTO = new LabTechnicianDTO();
        technicianDTO.setId(order.getLabTechnician().getId());
        technicianDTO.setFullName(order.getLabTechnician().getUser().getNames()); 
        response.setLabTechnician(technicianDTO);

        response.setStudies(
                order.getOrderStudies().stream().map(os -> {

                    OrderStudyResponseDTO s = new OrderStudyResponseDTO();
                    s.setId(os.getId());
                    s.setStatus(os.getStatus());
                    s.setNotes(os.getNotes());

                    StudyDTO studyDTO = new StudyDTO();
                    studyDTO.setId(os.getStudy().getId());
                    studyDTO.setName(os.getStudy().getName()); 
                    s.setStudy(studyDTO);

                    s.setParameters(
                            os.getStudy().getStudyParameters().stream().map(p -> {
                                ParameterDTO pDTO = new ParameterDTO();
                                pDTO.setId(p.getParameter().getId());
                                pDTO.setName(p.getParameter().getName());
                                pDTO.setUnit(p.getParameter().getUnit());
                                return pDTO;
                            }).toList());

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
        // existingOrder.setStatus(order.getStatus()); SOLO SE PUEDE CAMBIAR EL STATUS CON updateStatus

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

    @Override
    public OrderResponseDTO updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        return toDTO(order);
    }

    private OrderResponseDTO toDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setRequestDateTime(order.getRequestDateTime());
        dto.setStatus(order.getStatus());
        dto.setNotes(order.getNotes());
        
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(order.getPatient().getId());
        patientDTO.setFullName(order.getPatient().getUser().getNames());
        dto.setPatient(patientDTO);

        LabTechnicianDTO techDTO = new LabTechnicianDTO();
        techDTO.setId(order.getLabTechnician().getId());
        techDTO.setFullName(order.getLabTechnician().getUser().getNames());
        dto.setLabTechnician(techDTO);

        List<OrderStudyResponseDTO> studies = new ArrayList<>();
        for (OrderStudy os : order.getOrderStudies()) {
            OrderStudyResponseDTO studyDTO = new OrderStudyResponseDTO();
            studyDTO.setId(os.getId());
            studyDTO.setStatus(os.getStatus());
            studyDTO.setNotes(os.getNotes());

            StudyDTO study = new StudyDTO();
            study.setId(os.getStudy().getId());
            study.setName(os.getStudy().getName());

            studyDTO.setStudy(study);
            studies.add(studyDTO);
        }
        dto.setStudies(studies);
        
        return dto;
    }
}
