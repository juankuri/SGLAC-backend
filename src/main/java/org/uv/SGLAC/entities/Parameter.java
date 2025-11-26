package org.uv.SGLAC.entities;

import jakarta.persistence.*;

//TODO: add constraints and relationships to Results, StudyParameter, ParameterRange entities
@Entity
@Table(name = "parameters")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "parameter_id_seq")
    @SequenceGenerator(name = "parameter_id_seq",
            sequenceName = "parameter_id_seq",
            allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit;

    public Parameter() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
