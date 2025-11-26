package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "study_id_fk", referencedColumnName = "study_id") // Foreign key to Study entity
    private Long studyId;

    @ManyToOne
    @JoinColumn(name = "paremeter_id_fk", referencedColumnName = "paremeter_id") // Foreign key to Parameter entity
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
