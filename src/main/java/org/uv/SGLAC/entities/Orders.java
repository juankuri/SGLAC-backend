package org.uv.SGLAC.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

//TODO: Add (or correct) relationship with other entities, such as Patient and "OrdenEstudio"
@Entity
@Table(name = "orders") 
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "orders_id_seq")
    @SequenceGenerator(name = "orders_id_seq",
            sequenceName = "orders_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "lab_technician_id", nullable = false)
    private LabTechnician labTechnician;

    @Column(name = "requestDateTime", nullable = false, updatable = false)
    private LocalDateTime requestDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_status_id", referencedColumnName = "order_status_id")
    private OrderStatus orderStatus;

    @Column(columnDefinition = "TEXT")
    private String notes;

    public Orders() {}

    public Orders(Long id, Patient patient, LabTechnician labTechnician, LocalDateTime requestDateTime,
            OrderStatus orderStatus, String notes) {
        this.id = id;
        this.patient = patient;
        this.labTechnician = labTechnician;
        this.requestDateTime = requestDateTime;
        this.orderStatus = orderStatus;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LabTechnician getLabTechnician() {
        return labTechnician;
    }

    public void setLabTechnician(LabTechnician labTechnician) {
        this.labTechnician = labTechnician;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
