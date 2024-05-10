package com.v1adem.polyclinic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
@NoArgsConstructor
@Getter
@Document(collection = "patient")
public class Patient {
    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("email")
    private String email;

    @Field("phone_number")
    private String phoneNumber;

    @Field("address")
    private String address;

    @Field("date_of_birth")
    private LocalDate dateOfBirth;

    @ReadOnlyProperty
    @DocumentReference(collection = "visit", lazy = true)
    private List<Visit> visits;
    @ReadOnlyProperty
    @DocumentReference(collection = "medical_record", lazy = true)
    private List<MedicalRecord> medicalRecords;
    @ReadOnlyProperty
    @DocumentReference(collection = "prescription", lazy = true)
    private List<Prescription> prescriptions;

    public Patient(String firstName, String lastName, String email, String phoneNumber, String address, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}

