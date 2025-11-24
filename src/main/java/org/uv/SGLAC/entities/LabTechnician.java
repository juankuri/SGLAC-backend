package org.uv.SGLAC.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;

//TODO: Add relationship with other entities, such as testOrders, User and enum Specialty
//TODO: Add enum Specialty
@Table
@Entity(name = "lab_technicians")
public class LabTechnician implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lab_technician_id_seq")
    @SequenceGenerator(name = "lab_technician_id_seq",
            sequenceName = "lab_technician_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id")
    private Speciality Speciality;

    //TODO: Delete this field from data model 
    // @Column(unique = true, nullable = false)
    // private String labTechnicianNumber;

    public LabTechnician() {
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

    public Speciality getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(Speciality speciality) {
        Speciality = speciality;
    }
    
}
