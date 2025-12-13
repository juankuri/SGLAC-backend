package org.uv.SGLAC.dtos;

import org.uv.SGLAC.entities.OrderStatus;
import java.util.List;

public class OrderStudyResponseDTO {

    private Long id;
    private StudyDTO study;
    private OrderStatus status;
    private String notes;
    private List<ParameterDTO> parameters;

    public OrderStudyResponseDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudyDTO getStudy() {
        return study;
    }

    public void setStudy(StudyDTO study) {
        this.study = study;
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

    public List<ParameterDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDTO> parameters) {
        this.parameters = parameters;
    }
}