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

@Table
@Entity(name = "patients")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "patient_id_seq")
    @SequenceGenerator(name = "patient_id_seq",
            sequenceName = "patient_id_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "patient_id")
    private Long id;

    @OneToOne
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "user_id",
        unique = true,
        nullable = false
    )
    private User user;    

    @Column(unique = true, nullable = false)
    private String recordNumber; //it works as a number to identify someone in the medical field (numero de expediente clinico)

    public Patient(User user, String recordNumber) {
        this.user = user;
        this.recordNumber = recordNumber;
    }

    public Patient() {
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
