package org.uv.SGLAC.dtos;

import org.uv.SGLAC.entities.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private PatientDTO patient;
    private LabTechnicianDTO labTechnician;
    private LocalDateTime requestDateTime;
    private OrderStatus status;
    private String notes;
    private List<OrderStudyResponseDTO> studies;

    public OrderResponseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public LabTechnicianDTO getLabTechnician() {
        return labTechnician;
    }

    public void setLabTechnician(LabTechnicianDTO labTechnician) {
        this.labTechnician = labTechnician;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderStudyResponseDTO> getStudies() {
        return studies;
    }

    public void setStudies(List<OrderStudyResponseDTO> studies) {
        this.studies = studies;
    }
}