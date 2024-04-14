package com.v1adem.polyclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
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
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Visit> visits;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<MedicalRecord> medicalRecords;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Prescription> prescriptions;

    public Doctor(String name,
                  String lastName,
                  String email,
                  String phoneNumber,
                  String specialization) {
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
