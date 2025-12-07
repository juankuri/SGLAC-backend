package org.uv.SGLAC.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "parameter_ranges")
public class ParameterRange {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "parameter_ranges_id_seq")
    @SequenceGenerator(name = "parameter_ranges_id_seq",
            sequenceName = "parameter_ranges_id_seq",
            allocationSize = 1)
    @Column(name = "parameter_range_id")
    private Long id;

    @Column(nullable = false)
    private float minValue;

    @Column(nullable = false)
    private float maxValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parameter_id", nullable = false,
                referencedColumnName = "parameter_id")
    private Parameter parameter;

    public ParameterRange() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}