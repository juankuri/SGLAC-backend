package org.uv.SGLAC.dtos;

import jakarta.validation.constraints.NotNull;

public class OrderStudyRequestDTO {

    @NotNull(message = "Study ID is required")
    private Long studyId;

    private String notes;

    public OrderStudyRequestDTO() {}

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}