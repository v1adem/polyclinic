package com.v1adem.polyclinic.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@Getter
@Document(collection = "doctor")
public class Doctor {
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

    @Field("specialization")
    private String specialization;

    @ReadOnlyProperty
    @DocumentReference(collection = "visit", lazy = true)
    private List<Visit> visits;
    @ReadOnlyProperty
    @DocumentReference(collection = "medical_record", lazy = true)
    private List<String> medicalRecords;
    @ReadOnlyProperty
    @DocumentReference(collection = "prescription", lazy = true)
    private List<String> prescriptions;

    public Doctor(String firstName, String lastName, String email, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}

