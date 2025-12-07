package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
        generator = "speciality_id_seq")
    @SequenceGenerator(name = "speciality_id_seq",
        sequenceName = "speciality_id_seq",
        allocationSize = 1)
    @Column(name = "speciality_id")
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;

    public Speciality() {
    }

    public Speciality(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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