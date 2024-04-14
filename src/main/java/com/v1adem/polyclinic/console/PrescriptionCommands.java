package com.v1adem.polyclinic.console;

import com.v1adem.polyclinic.entity.Prescription;
import com.v1adem.polyclinic.service.DoctorService;
import com.v1adem.polyclinic.service.PatientService;
import com.v1adem.polyclinic.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

import static com.v1adem.polyclinic.console.TableCreator.createPrescriptionTable;

@RequiredArgsConstructor
@ShellComponent
public class PrescriptionCommands {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final PrescriptionService prescriptionService;

    @ShellMethod(value = "Add a new prescription", key = "presc add")
    public void addPrescription() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Patient ID: ");
        long patientId = sc.nextLong();
        System.out.println("Doctor ID: ");
        long doctorId = sc.nextLong();
        sc.nextLine();
        System.out.println("Medication: ");
        String medication = sc.nextLine();
        System.out.println("Dosage: ");
        String dosage = sc.nextLine();
        System.out.println("Instructions: ");
        String instructions = sc.nextLine();

        Prescription prescription = new Prescription(
                patientService.findById(patientId),
                doctorService.findById(doctorId),
                medication, dosage, instructions);

        prescriptionService.add(prescription);
    }

    @ShellMethod(value = "Display information about a prescription by ID.", key = "presc show")
    public String showPrescriptionById(Long id) {
        Prescription prescriptions = prescriptionService.findById(id);
        if (prescriptions == null) {
            return "Prescription with ID " + id + " not found";
        }

        return createPrescriptionTable(List.of(prescriptions));
    }

    @ShellMethod(value = "Display all prescriptions.", key = "presc show all")
    public String showAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.findAll();
        if (prescriptions.isEmpty()) {
            return "No prescriptions found";
        }

        return createPrescriptionTable(prescriptions);
    }

    @ShellMethod(value = "Delete prescription by ID.", key = "presc del")
    public String deletePrescriptionById(Long id) {
        if (prescriptionService.deleteById(id)) {
            return "Prescription with ID " + id + " deleted successfully";
        } else {
            return "Prescription with ID " + id + " not found";
        }
    }

    @ShellMethod(value = "Get total number of prescriptions.", key = "presc count")
    public String getTotalNumberOfPrescriptions() {
        long totalPrescriptions = prescriptionService.getTotalNumber();
        return "Total number of prescriptions: " + totalPrescriptions;
    }
}