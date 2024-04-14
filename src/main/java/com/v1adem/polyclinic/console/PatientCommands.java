package com.v1adem.polyclinic.console;

import com.v1adem.polyclinic.entity.Patient;
import com.v1adem.polyclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static com.v1adem.polyclinic.console.TableCreator.*;

@RequiredArgsConstructor
@ShellComponent
public class PatientCommands {
    private final PatientService patientService;

    @ShellMethod(value = "Add a new patient", key = "patient add")
    public void addPatient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = sc.nextLine();
        System.out.println("Last Name: ");
        String lastName = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Address: ");
        String address = sc.nextLine();
        System.out.println("Date of Birth (dd/mm/yyyy): ");
        String dateOfBirth = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Patient patient = new Patient(firstName,
                lastName,
                email,
                phoneNumber,
                address,
                LocalDate.parse(dateOfBirth, formatter));
        patientService.add(patient);
    }

    @ShellMethod(value = "Display information about a patient by ID.", key = "patient show")
    public String showPatientById(Long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return "Patient with ID " + id + " not found";
        }

        return createPatientTable(List.of(patient));
    }

    @ShellMethod(value = "Display all patients.", key = "patient show all")
    public String showAllPatients() {
        List<Patient> patients = patientService.findAll();
        if (patients.isEmpty()) {
            return "No patients found";
        }

        return createPatientTable(patients);
    }

    @ShellMethod(value = "Delete patient by ID.", key = "patient del")
    public String deletePatientById(Long id) {
        if (patientService.deleteById(id)) {
            return "Patient with ID " + id + " deleted successfully";
        } else {
            return "Patient with ID " + id + " not found";
        }
    }

    @ShellMethod(value = "Get total number of patients.", key = "patient count")
    public String getTotalNumberOfPatients() {
        long totalPatients = patientService.getTotalNumber();
        return "Total number of patients: " + totalPatients;
    }

    @ShellMethod(value = "Get total number of visits for individual patient.", key = "patient visit count")
    public String getTotalNumberOfVisitsForPatient(long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return "Patient with ID " + id + " not found";
        }
        return "Total number of visits for " + patient.getLastName() + " : " + patient.getVisits().size();
    }

    @ShellMethod(value = "Get all visits for individual patient.", key = "patient visit")
    public String getAllVisitsForPatient(long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return "Patient with ID " + id + " not found";
        }
        System.out.println(
                "Patient " + patient.getLastName() + " " + patient.getFirstName()
                        + " has " + patient.getVisits().size() + " visits:");
        return createVisitTable(patient.getVisits());
    }

    @ShellMethod(value = "Get total number of prescriptions for individual patient.", key = "patient presc count")
    public String getTotalNumberOfPrescriptionsForPatient(long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return "Patient with ID " + id + " not found";
        }
        return "Total number of prescriptions for " + patient.getLastName() + " : " + patient.getPrescriptions().size();
    }

    @ShellMethod(value = "Get all visits for prescriptions patient.", key = "patient presc")
    public String getAllPrescriptionsForPatient(long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return "Patient with ID " + id + " not found";
        }
        System.out.println(
                "Patient " + patient.getLastName() + " " + patient.getFirstName()
                        + " has " + patient.getVisits().size() + " prescriptions:");
        return createPrescriptionTable(patient.getPrescriptions());
    }

    @ShellMethod(value = "Get total number of medical records for individual patient.", key = "patient medrec count")
    public String getTotalNumberOfMedicalRecordsForPatient(long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return "Patient with ID " + id + " not found";
        }
        return "Total number of medical records for " + patient.getLastName() + " : " + patient.getMedicalRecords().size();
    }

    @ShellMethod(value = "Get all visits for individual patient.", key = "patient medrec")
    public String getAllMedicalRecordsForPatient(long id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return "Patient with ID " + id + " not found";
        }
        System.out.println(
                "Patient " + patient.getLastName() + " " + patient.getFirstName()
                        + " has " + patient.getVisits().size() + " medical records:");
        return createMedicalRecordTable(patient.getMedicalRecords());
    }
}
