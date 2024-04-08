package com.v1adem.polyclinic.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
    @Column(name = "medicalRecord_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
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
