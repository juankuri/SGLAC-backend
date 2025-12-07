package org.uv.SGLAC.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
        generator = "result_id_seq")
    @SequenceGenerator (name = "result_id_seq",
        sequenceName = "result_id_seq",
        initialValue = 1,
        allocationSize = 1)    
    @Column(name = "result_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_study_id", nullable = false)
    private OrderStudy orderStudy;

    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    @Column(name = "value", nullable = false, precision = 12, scale = 4)
    private BigDecimal value;

    @Column(name = "record_date", nullable = false)
    private LocalDateTime recordDate;

    @Column(name = "validated", nullable = false)
    private Boolean validated;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public Result() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStudy getOrderStudy() {
        return orderStudy;
    }

    public void setOrderStudy(OrderStudy orderStudy) {
        this.orderStudy = orderStudy;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
