package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table
@Entity(name="study_parameters")
public class StudyParameter {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "study_parameters_id_seq")
    @SequenceGenerator(name = "study_parameters_id_seq",
            sequenceName = "study_parameters_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    //TODO: Add relationships to Study and Parameter entities
    private Long studyId;
    private Long parameterId;

    //TODO: "NOTES" field MUST BE DELLETED FROM DATA MODEL 

    public StudyParameter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    
}
