package org.uv.SGLAC.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "test_parameter")
public class TestParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "test_parameter_id_seq")
    @SequenceGenerator(name = "test_parameter_id_seq",
            sequenceName = "test_parameter_id_seq",
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    public TestParameter() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    
}
