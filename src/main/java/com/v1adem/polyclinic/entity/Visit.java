package com.v1adem.polyclinic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;

@NoArgsConstructor
@Getter
@Document(collection = "visit")
public class Visit {
    @Id
    private String id;

    @DocumentReference(collection = "patient", lazy = true)
    private Patient patient;

    @DocumentReference(collection = "doctor", lazy = true)
    private Doctor doctor;

    @Field("reason")
    private String reason;

    @Setter
    @Field("diagnosis")
    private String diagnosis;

    @Field("treatment")
    private String treatment;

    @Field("date_created")
    private Date dateCreated;

    public Visit(Patient patient, Doctor doctor, String reason, String diagnosis, String treatment) {
        this.patient = patient;
        this.doctor = doctor;
        this.reason = reason;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.dateCreated = new Date();  // Initialize with current date
    }
}

