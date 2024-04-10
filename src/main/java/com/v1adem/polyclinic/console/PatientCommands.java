package com.v1adem.polyclinic.console;

import com.v1adem.polyclinic.entity.Patient;
import com.v1adem.polyclinic.service.PatientService;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

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
        patientService.addPatient(patient);
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
        if (patientService.deletePatientById(id)) {
            return "Patient with ID " + id + " deleted successfully";
        } else {
            return "Patient with ID " + id + " not found";
        }
    }

    @ShellMethod(value = "Get total number of patients.", key = "patient count")
    public String getTotalNumberOfPatients() {
        long totalPatients = patientService.getTotalNumberOfPatients();
        return "Total number of patients: " + totalPatients;
    }

    private String createPatientTable(List<Patient> patients){
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
}
