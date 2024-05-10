package com.v1adem.polyclinic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;

@NoArgsConstructor
@Getter
@Document(collection = "prescription")
public class Prescription {
    @Id
    private String id;

    @DocumentReference(collection = "patient")
    private Patient patient;

    @DocumentReference(collection = "doctor")
    private Doctor doctor;

    @Field("medication")
    private String medication;

    @Field("dosage")
    private String dosage;

    @Field("instructions")
    private String instructions;

    @Field("date_created")
    private Date dateCreated;

    public Prescription(Patient patient, Doctor doctor, String medication, String dosage, String instructions) {
        this.patient = patient;
        this.doctor = doctor;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.dateCreated = new Date();  // Initialize with current date
    }
}

