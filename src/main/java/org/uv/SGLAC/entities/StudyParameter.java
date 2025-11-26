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
    private int studyId;
    private int parameterId;

    //TODO: "NOTES" field MUST BE DELLETED FROM DATA MODEL 
}
