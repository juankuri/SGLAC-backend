package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;

//TODO: Add relationship with User entity
@Table
@Entity(name = "patients")
public class Patient implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "patient_id_seq")
    @SequenceGenerator(name = "patient_id_seq",
            sequenceName = "patient_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;    

    @Column(unique = true, nullable = false)
    private String recordNumber; //it works as a number to identify someone in the medical field (numero de expediente clinico)

    public Patient(Long id, User user, String recordNumber) {
        this.id = id;
        this.user = user;
        this.recordNumber = recordNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    
}
