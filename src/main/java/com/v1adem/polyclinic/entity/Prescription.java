package com.v1adem.polyclinic.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class Prescription {
    @Id
    @GeneratedValue
    @Column(name = "prescription_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @Column(nullable = false)
    private String medication;
    @Column(nullable = false)
    private String dosage;
    @Column(nullable = false)
    private String instructions;
    @Column(nullable = false)
    private Timestamp dateCreated;

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Timestamp(LocalDate.now().toEpochDay());
    }
}
