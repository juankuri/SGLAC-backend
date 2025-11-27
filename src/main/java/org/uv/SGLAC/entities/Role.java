package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//TODO: convert to Entity and add to data model diagram
@Table
@Entity
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
        generator = "patient_id_seq")
    @SequenceGenerator (name = "patient_id_seq",
        sequenceName = "patient_id_seq",
        initialValue = 1,
        allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Role() {}

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
