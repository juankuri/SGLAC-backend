package org.uv.SGLAC.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

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
    @JoinColumn(name = "pacient_id", nullable = false)
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "laboratorista_id", nullable = false)
    private Employees employee;

    @Column(name = "requestDateTime", nullable = false, updatable = false)
    private LocalDateTime requestDateTime = LocalDateTime.now();

    public Orders() {}

    public Orders(Long id, Pacient pacient, Employees employee, LocalDateTime requestDateTime) {
        this.id = id;
        this.pacient = pacient;
        this.employee = employee;
        this.requestDateTime = requestDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}
