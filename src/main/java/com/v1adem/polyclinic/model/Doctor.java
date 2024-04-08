package com.v1adem.polyclinic.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue
    @Column(name = "doctor_id")
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
    private String specialization;
    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits;
    @OneToMany(mappedBy = "doctor")
    private List<MedicalRecord> medicalRecords;
    @OneToMany(mappedBy = "doctor")
    private List<Prescription> prescriptions;
}
