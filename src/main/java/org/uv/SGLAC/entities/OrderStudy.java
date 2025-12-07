package org.uv.SGLAC.entities;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "order_studies")
public class OrderStudy implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "order_studies_id_seq")
    @SequenceGenerator(
            name = "order_studies_id_seq",
            sequenceName = "order_studies_id_seq",
            allocationSize = 1
    )
    @Column(name = "order_study_id")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "order_id",
            nullable = false
    )
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "study_id",
            referencedColumnName = "order_id",
            nullable = false
    )
    private Study study;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "notes")
    private String notes;

    // Constructors
    public OrderStudy() {
        // Default constructor
    }

    public OrderStudy(Order order, Study study, OrderStatus status) {
        this.order = order;
        this.study = study;
        this.status = status;
    }

    public OrderStudy(Order order, Study study, OrderStatus status, String notes) {
        this.order = order;
        this.study = study;
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "OrderStudy{" +
                "id=" + id +
                ", orderId=" + (order != null ? order.getId() : null) +
                ", studyId=" + (study != null ? study.getId() : null) +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                '}';
    }
}