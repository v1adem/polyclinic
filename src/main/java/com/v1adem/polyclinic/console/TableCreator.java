package com.v1adem.polyclinic.console;

import com.v1adem.polyclinic.entity.*;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

import java.util.List;

public class TableCreator {

    static String createPatientTable(List<Patient> patients){
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID",
                "First Name", "Last Name",
                "Email", "Phone Number",
                "Address", "Date of Birth");
        table.addRule();
        for (Patient patient : patients) {
            table.addRow(patient.getId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getEmail(),
                    patient.getPhoneNumber(),
                    patient.getAddress(),
                    patient.getDateOfBirth());
            table.addRule();
        }

        table.getRenderer().setCWC(new CWC_LongestLine());

        return table.render();
    }

    static String createDoctorTable(List<Doctor> doctors) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "FirstName", "SecondName", "Email", "Phone Number", "Specialization");
        table.addRule();
        for (Doctor doctor : doctors) {
            table.addRow(doctor.getId(),
                    doctor.getFirstName(),
                    doctor.getLastName(),
                    doctor.getEmail(),
                    doctor.getPhoneNumber(),
                    doctor.getSpecialization());
            table.addRule();
        }

        table.getRenderer().setCWC(new CWC_LongestLine());

        return table.render();
    }

    static String createVisitTable(List<Visit> visits){
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID",
                "Patient ID", "Doctor ID",
                "Reason", "Diagnosis",
                "Treatment", "Date Created");
        table.addRule();
        for (Visit visit : visits) {
            table.addRow(visit.getId(),
                    visit.getPatient().getId(),
                    visit.getDoctor().getId(),
                    visit.getReason(),
                    visit.getDiagnosis(),
                    visit.getTreatment(),
                    visit.getDateCreated());
            table.addRule();
        }

        table.getRenderer().setCWC(new CWC_LongestLine());

        return table.render();
    }

    static String createPrescriptionTable(List<Prescription> prescriptions){
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID",
                "Patient ID", "Doctor ID",
                "Medication", "Dosage",
                "Instructions", "Date Created");
        table.addRule();
        for (Prescription prescription : prescriptions) {
            table.addRow(prescription.getId(),
                    prescription.getPatient().getId(),
                    prescription.getDoctor().getId(),
                    prescription.getMedication(),
                    prescription.getDosage(),
                    prescription.getInstructions(),
                    prescription.getDateCreated());
            table.addRule();
        }

        table.getRenderer().setCWC(new CWC_LongestLine());

        return table.render();
    }

    static String createMedicalRecordTable(List<MedicalRecord> medicalRecords){
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID",
                "Patient ID", "Doctor ID",
                "Diagnosis", "Treatment",
                "Date Created");
        table.addRule();
        for (MedicalRecord medicalRecord: medicalRecords) {
            table.addRow(medicalRecord.getId(),
                    medicalRecord.getPatient().getId(),
                    medicalRecord.getDoctor().getId(),
                    medicalRecord.getDiagnosis(),
                    medicalRecord.getTreatment(),
                    medicalRecord.getDateCreated());
            table.addRule();
        }

        table.getRenderer().setCWC(new CWC_LongestLine());

        return table.render();
    }
}
