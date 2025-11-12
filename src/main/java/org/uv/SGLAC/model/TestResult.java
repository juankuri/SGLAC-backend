package org.uv.SGLAC.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tests_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_result_id_seq")
    @SequenceGenerator(name = "test_result_id_seq", sequenceName = "test_result_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_order_id", referencedColumnName = "id", nullable = false)
    private TestOrder testOrder; // ordenServicioId

    @Column(nullable = false)
    private String obtainedValues; // valoresObtenidos

    @Column(nullable = false)
    private LocalDateTime processedDateTime; // fechaHoraProcesamiento

    @ManyToOne
    @JoinColumn(name = "technician_id", referencedColumnName = "id", nullable = false)
    private Employees employee; // laboratoristaId

    // @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    // private ValidationStatus validationStatus; // estadoValidacion

    @Column(length = 500)
    private String technicalNotes; // observacionesTecnicas

    // Getters y setters
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

    public String getObtainedValues() {
        return obtainedValues;
    }

    public void setObtainedValues(String obtainedValues) {
        this.obtainedValues = obtainedValues;
    }

    public LocalDateTime getProcessedDateTime() {
        return processedDateTime;
    }

    public void setProcessedDateTime(LocalDateTime processedDateTime) {
        this.processedDateTime = processedDateTime;
    }

    public LaboratoryTechnician getTechnician() {
        return technician;
    }

    public void setTechnician(LaboratoryTechnician technician) {
        this.technician = technician;
    }

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getTechnicalNotes() {
        return technicalNotes;
    }

    public void setTechnicalNotes(String technicalNotes) {
        this.technicalNotes = technicalNotes;
    }
}
