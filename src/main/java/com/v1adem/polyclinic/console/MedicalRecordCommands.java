package com.v1adem.polyclinic.console;

import com.v1adem.polyclinic.entity.MedicalRecord;
import com.v1adem.polyclinic.service.DoctorService;
import com.v1adem.polyclinic.service.MedicalRecordService;
import com.v1adem.polyclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

import static com.v1adem.polyclinic.console.TableCreator.createMedicalRecordTable;

@RequiredArgsConstructor
@ShellComponent
public class MedicalRecordCommands {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final MedicalRecordService medicalRecordService;

    @ShellMethod(value = "Add a new medical record", key = "presc add")
    public void addMedicalRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Patient ID: ");
        String patientId = sc.nextLine();
        System.out.println("Doctor ID: ");
        String doctorId = sc.nextLine();
        sc.nextLine();
        System.out.println("Diagnosis: ");
        String diagnosis = sc.nextLine();
        System.out.println("Treatment: ");
        String treatment = sc.nextLine();

        MedicalRecord medicalRecord = new MedicalRecord(
                patientService.findById(patientId),
                doctorService.findById(doctorId),
                diagnosis, treatment);

        medicalRecordService.add(medicalRecord);
    }

    @ShellMethod(value = "Display information about a medical record by ID.", key = "presc show")
    public String showMedicalRecordById(String id) {
        MedicalRecord medicalRecords = medicalRecordService.findById(id);
        if (medicalRecords == null) {
            return "MedicalRecord with ID " + id + " not found";
        }

        return createMedicalRecordTable(List.of(medicalRecords));
    }

    @ShellMethod(value = "Display all medical records.", key = "presc show all")
    public String showAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordService.findAll();
        if (medicalRecords.isEmpty()) {
            return "No medical records found";
        }

        return createMedicalRecordTable(medicalRecords);
    }

    @ShellMethod(value = "Delete medical record by ID.", key = "presc del")
    public String deleteMedicalRecordById(String id) {
        if (medicalRecordService.deleteById(id)) {
            return "Medical record with ID " + id + " deleted successfully";
        } else {
            return "Medical record with ID " + id + " not found";
        }
    }

    @ShellMethod(value = "Get total number of medical records.", key = "presc count")
    public String getTotalNumberOfMedicalRecords() {
        long totalMedicalRecords = medicalRecordService.getTotalNumber();
        return "Total number of medical records: " + totalMedicalRecords;
    }
}