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
@Document(collection = "medical_record")
public class MedicalRecord {
    @Id
    private String id;

    @DocumentReference(collection = "patient")
    private Patient patient;

    @DocumentReference(collection = "doctor")
    private Doctor doctor;

    @Field("diagnosis")
    private String diagnosis;

    @Field("treatment")
    private String treatment;

    @Field("date_created")
    private Date dateCreated;

    public MedicalRecord(Patient patient, Doctor doctor, String diagnosis, String treatment) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.dateCreated = new Date();
    }
}

