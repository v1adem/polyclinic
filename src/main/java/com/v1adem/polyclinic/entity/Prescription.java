package com.v1adem.polyclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
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

    public Prescription(Patient patient,
                        Doctor doctor,
                        String medication,
                        String dosage,
                        String instructions) {
        this.patient = patient;
        this.doctor = doctor;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Timestamp(LocalDate.now().toEpochDay());
    }
}
