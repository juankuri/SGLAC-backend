package org.uv.SGLAC.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "result_id_seq")
    @SequenceGenerator(name = "result_id_seq",
            sequenceName = "result_id_seq",
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_order_id", nullable = false)
    private TestOrder testOrder;

    @Column(nullable = false)
    private String value;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column
    private LocalDateTime record_date;

    @Column(nullable = false)
    private boolean isValidated = false;

    @Column(nullable = true)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    
    public Result() {}


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public TestOrder getTestOrder() {
        return testOrder;
    }


    public void setTestOrder(TestOrder testOrder) {
        this.testOrder = testOrder;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public LocalDateTime getRecord_date() {
        return record_date;
    }


    public void setRecord_date(LocalDateTime record_date) {
        this.record_date = record_date;
    }


    public boolean isValidated() {
        return isValidated;
    }


    public void setValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }


    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Parameter getParameter() {
        return parameter;
    }


    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

}
