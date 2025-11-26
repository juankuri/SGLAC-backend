package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//TODO: Add relationships to Parameter and StudyParameter entities
@Table
@Entity(name="parameter_ranges")
public class ParameterRange {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "parameters_id_seq")
    @SequenceGenerator(name = "parameters_id_seq",
            sequenceName = "parameters_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private float minValue;

    @Column(nullable = false)
    private float maxValue;
}
