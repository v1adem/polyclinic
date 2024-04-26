package com.v1adem.polyclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "patient",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Visit> visits;
    @OneToMany(mappedBy = "patient",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<MedicalRecord> medicalRecords;
    @OneToMany(mappedBy = "patient",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Prescription> prescriptions;


    public Patient(String firstname,
                   String lastname,
                   String email,
                   String phone,
                   String address,
                   LocalDate dateOfBirth) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.phoneNumber = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}

