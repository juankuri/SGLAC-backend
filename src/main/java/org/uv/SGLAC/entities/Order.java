package org.uv.SGLAC.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lab_technician_id", referencedColumnName = "lab_technician_id", nullable = false)
    private LabTechnician labTechnician;

    @Column(name = "request_date_time", nullable = false, updatable = false)
    private LocalDateTime requestDateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderStudy> orderStudies = new ArrayList<>();

    public Order() {
    }

    public Order(Patient patient, LabTechnician labTechnician, LocalDateTime requestDateTime, String notes,
            List<OrderStudy> orderStudies) {
        this.patient = patient;
        this.labTechnician = labTechnician;
        this.requestDateTime = requestDateTime;
        this.status = OrderStatus.CREATED;
        this.notes = notes;
        this.orderStudies = orderStudies;
    }

    // por si acaso
    public void addOrderStudy(OrderStudy orderStudy) {
        if (orderStudies == null) {
            orderStudies = new ArrayList<>();
        }
        orderStudies.add(orderStudy);
        orderStudy.setOrder(this);
    }

    public void removeOrderStudy(OrderStudy orderStudy) {
        orderStudies.remove(orderStudy);
        orderStudy.setOrder(null);
    }

    public Long getId() {
        return id;
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

    // Method to update requestDateTime to current date and time
    public void updateRequestDateTimeToNow() {
        this.requestDateTime = LocalDateTime.now();
    }

    public List<OrderStudy> getOrderStudies() {
        return orderStudies;
    }

    public void setOrderStudies(List<OrderStudy> orderStudies) {
        this.orderStudies = orderStudies;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
