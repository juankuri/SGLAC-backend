package org.uv.SGLAC.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "doctor_id_seq")
    @SequenceGenerator(
            name = "doctor_id_seq",
            sequenceName = "doctor_id_seq",
            allocationSize = 1)
    @Column(name = "doctor_id")
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id", nullable = false)
    private Speciality speciality;

    @Column(name = "id_proffesional", nullable = false)
    private Long professionalId;

    public Doctor() {
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
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Long getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(Long professionalId) {
        this.professionalId = professionalId;
    }

}

