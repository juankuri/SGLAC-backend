package org.uv.SGLAC.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "studies_id_seq")
    @SequenceGenerator(
            name = "studies_id_seq",
            sequenceName = "studies_id_seq",
            allocationSize = 1
    )
    @Column(name = "study_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "study")
    private List<OrderStudy> orderStudies;

    @OneToMany(mappedBy = "study")
    private List<StudyParameter> studyParameters;

    public Study() {}

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

    public List<OrderStudy> getOrderStudies() {
        return orderStudies;
    }

    public void setOrderStudies(List<OrderStudy> orderStudies) {
        this.orderStudies = orderStudies;
    }

    public List<StudyParameter> getStudyParameters() {
        return studyParameters;
    }

    public void setStudyParameters(List<StudyParameter> studyParameters) {
        this.studyParameters = studyParameters;
    }
}
