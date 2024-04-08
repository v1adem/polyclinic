package com.v1adem.polyclinic.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
@Entity
public class Visit {
    @Id
    @GeneratedValue
    @Column(name = "visit_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private String diagnosis;
    @Column(nullable = false)
    private String treatment;
    @Column(nullable = false)
    private Timestamp dateCreated;

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Timestamp(LocalDate.now().toEpochDay());
    }
}
