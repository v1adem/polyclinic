package com.v1adem.polyclinic.console;

import com.v1adem.polyclinic.entity.Visit;
import com.v1adem.polyclinic.service.DoctorService;
import com.v1adem.polyclinic.service.PatientService;
import com.v1adem.polyclinic.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

import static com.v1adem.polyclinic.console.TableCreator.createVisitTable;

@RequiredArgsConstructor
@ShellComponent
public class VisitCommands {
    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @ShellMethod(value = "Add a new visit", key = "visit add")
    public void addVisit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Patient ID: ");
        long patientId = sc.nextLong();
        System.out.println("Doctor ID: ");
        long doctorId = sc.nextLong();
        sc.nextLine();
        System.out.println("Reason: ");
        String reason = sc.nextLine();
        System.out.println("Diagnosis: ");
        String diagnosis = sc.nextLine();
        System.out.println("Treatment: ");
        String treatment = sc.nextLine();

        Visit visit = new Visit(
                patientService.findById(patientId),
                doctorService.findById(doctorId),
                reason, diagnosis, treatment);

        visitService.addVisit(visit);
    }

    @ShellMethod(value = "Display information about a visit by ID.", key = "visit show")
    public String showVisitById(Long id) {
        Visit visit = visitService.findById(id);
        if (visit == null) {
            return "Visit with ID " + id + " not found";
        }

        return createVisitTable(List.of(visit));
    }

    @ShellMethod(value = "Display all visits.", key = "visit show all")
    public String showAllVisits() {
        List<Visit> visits = visitService.findAll();
        if (visits.isEmpty()) {
            return "No visits found";
        }

        return createVisitTable(visits);
    }

    @ShellMethod(value = "Delete visit by ID.", key = "visit del")
    public String deleteVisitById(Long id) {
        if (visitService.deleteVisitById(id)) {
            return "Visit with ID " + id + " deleted successfully";
        } else {
            return "Visit with ID " + id + " not found";
        }
    }

    @ShellMethod(value = "Get total number of visits.", key = "visit count")
    public String getTotalNumberOfVisits() {
        long totalVisits = visitService.getTotalNumberOfVisits();
        return "Total number of visits: " + totalVisits;
    }

    @ShellMethod(value = "Update diagnosis", key = "visit update di")
    public String updateDiagnosis(Long id) {
        Visit visit = visitService.findById(id);
        Scanner sc = new Scanner(System.in);
        System.out.println("New diagnosis: ");
        String newDiagnosis = sc.nextLine();
        visit.setDiagnosis(newDiagnosis);
        visitService.update(visit);
        return createVisitTable(List.of(visitService.findById(id)));
    }
}
