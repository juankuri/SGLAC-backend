package org.uv.SGLAC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table
@Entity(name="reagents")
public class Reagent {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reagents_id_seq")
    @SequenceGenerator(name = "reagents_id_seq",
            sequenceName = "reagents_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column 
    private String name;

    @Column
    private String description;
}
