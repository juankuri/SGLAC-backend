package org.uv.SGLAC.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "parameters")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "parameter_id_seq")
    @SequenceGenerator(name = "parameter_id_seq",
            sequenceName = "parameter_id_seq",
            allocationSize = 1)
    @Column(name = "parameter_id")
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 50)
    private String unit;

    @OneToMany(mappedBy = "parameter")
    private List<StudyParameter> studyParameters;

    @OneToMany(mappedBy = "parameter")
    private List<ParameterRange> parameterRanges;

    @OneToMany(mappedBy = "parameter")
    private List<Result> results;

    public Parameter() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<StudyParameter> getStudyParameters() {
        return studyParameters;
    }

    public void setStudyParameters(List<StudyParameter> studyParameters) {
        this.studyParameters = studyParameters;
    }

    public List<ParameterRange> getParameterRanges() {
        return parameterRanges;
    }

    public void setParameterRanges(List<ParameterRange> parameterRanges) {
        this.parameterRanges = parameterRanges;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
