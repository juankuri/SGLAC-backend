package org.uv.SGLAC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table
@Entity(name="tests")
public class Test {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "test_id_seq")
    @SequenceGenerator(name = "test_id_seq",
            sequenceName = "test_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;
}
