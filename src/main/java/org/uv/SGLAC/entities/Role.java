package org.uv.SGLAC.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table
@Entity
public class Role implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
        generator = "role_id_seq")
    @SequenceGenerator (name = "role_id_seq",
        sequenceName = "role_id_seq",
        initialValue = 1,
        allocationSize = 1)
    @Column(name = "role_id")
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
