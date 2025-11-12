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
import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Entity(name = "pacients")
public class Pacient implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "pacient_id_seq")
    @SequenceGenerator(name = "pacient_id_seq",
            sequenceName = "pacient_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;

    @Column(name = "dateOfBirth", nullable = false, updatable = false)
    private LocalDateTime dateOfBirth = LocalDateTime.now();

    @Column
    private Sex sex;

    @Column(unique = true, nullable = false)
    private String recordNumber; //it works as a number to identify someone in the medical field (numero de expediente clinico)

}
