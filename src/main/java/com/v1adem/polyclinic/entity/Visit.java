package com.v1adem.polyclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @Column(nullable = false)
    private String reason;
    @Setter
    @Column(nullable = false)
    private String diagnosis;
    @Column(nullable = false)
    private String treatment;
    @Column(nullable = false)
    private Timestamp dateCreated;

    public Visit(Patient patient,
                 Doctor doctor,
                 String reason,
                 String diagnosis,
                 String treatment) {
        this.patient = patient;
        this.doctor = doctor;
        this.reason = reason;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Timestamp(LocalDate.now().toEpochDay());
    }
}
