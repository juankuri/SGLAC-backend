package org.uv.SGLAC.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table
@Entity (name = "test_orders")
public class TestOrder {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "test_order_id_seq")
    @SequenceGenerator(name = "test_order_id_seq",
            sequenceName = "test_order_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacient_id", referencedColumnName = "id")
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employees employee;

    @Column(name = "requestDateTime", nullable = false, updatable = false)
    private LocalDateTime requestDateTime = LocalDateTime.now();

    @Column
    private OrderStatus orderStatus;

    @Column
    private String notes;

}
