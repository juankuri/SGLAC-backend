package org.uv.SGLAC.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_order")
public class TestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "test_order_id_seq")
    @SequenceGenerator(name = "test_order_id_seq",
            sequenceName = "test_order_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "estudio_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private OrderStatus orderStatus;

    @Column
    private String notes;

    @Column(name = "requestDateTime", nullable = false, updatable = false)
    private LocalDateTime requestDateTime = LocalDateTime.now();

    public TestOrder() {
    }

    public TestOrder(Long id, Orders order, Test test, OrderStatus orderStatus, String notes,
                LocalDateTime requestDateTime) {
        this.id = id;
        this.order = order;
        this.test = test;
        this.orderStatus = orderStatus;
        this.notes = notes;
        this.requestDateTime = requestDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    
}
