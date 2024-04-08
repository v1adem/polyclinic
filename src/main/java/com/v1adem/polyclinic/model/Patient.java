package com.v1adem.polyclinic.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "patient_id")
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;
    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecords;
    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;
}

