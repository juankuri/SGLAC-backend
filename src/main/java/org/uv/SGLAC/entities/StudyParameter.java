package org.uv.SGLAC.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "study_parameters")
public class StudyParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "study_parameters_id_seq")
    @SequenceGenerator(
            name = "study_parameters_id_seq",
            sequenceName = "study_parameters_id_seq",
            allocationSize = 1
    )
    @Column(name = "study_parameter_id")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "study_id",
            referencedColumnName = "study_id",
            nullable = false
    )
    private Study study;

    @ManyToOne
    @JoinColumn(
            name = "parameter_id",
            referencedColumnName = "parameter_id",
            nullable = false
    )
    private Parameter parameter;

    public StudyParameter() {}

    public Long getId() {
        return id;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
