package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//TODO: Add relationships to OrderStudy and StudyParameter entities
@Table
@Entity(name="studies")
public class Study {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "studies_id_seq")
    @SequenceGenerator(name = "studies_id_seq",
            sequenceName = "studies_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Study() {}

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
}
