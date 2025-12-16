package org.uv.SGLAC.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderRequestDTO {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Lab Technician ID is required")
    private Long labTechnicianId;

    @NotEmpty(message = "At least one study is required")
    private List<OrderStudyRequestDTO> studies;

    private String notes;

    public OrderRequestDTO() {}

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getLabTechnicianId() {
        return labTechnicianId;
    }

    public void setLabTechnicianId(Long labTechnicianId) {
        this.labTechnicianId = labTechnicianId;
    }

    public List<OrderStudyRequestDTO> getStudies() {
        return studies;
    }

    public void setStudies(List<OrderStudyRequestDTO> studies) {
        this.studies = studies;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}