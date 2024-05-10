package com.v1adem.polyclinic.console;

import com.v1adem.polyclinic.entity.Doctor;
import com.v1adem.polyclinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Scanner;

import static com.v1adem.polyclinic.console.TableCreator.createDoctorTable;

@RequiredArgsConstructor
@ShellComponent
public class DoctorCommands {
    private final DoctorService doctorService;

    @ShellMethod(value = "Add a new doctor", key = "doctor add")
    public void addDoctor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Specialization: ");
        String specialization = sc.nextLine();
        Doctor doctor = new Doctor(firstName,
                lastName,
                email,
                phoneNumber,
                specialization);
        doctorService.add(doctor);
    }

    @ShellMethod(value = "Display information about a doctor by ID.", key = "doctor show")
    public String showDoctorById(String id) {
        Doctor doctor = doctorService.findById(id);
        if (doctor == null) {
            return "Doctor with ID " + id + " not found";
        }
        return createDoctorTable(List.of(doctor));
    }

    @ShellMethod(value = "Display all doctors.", key = "doctor show all")
    public String showAllDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        if (doctors.isEmpty()) {
            return "No doctors found";
        }

        return createDoctorTable(doctors);
    }

    @ShellMethod(value = "Delete doctor by ID.", key = "doctor del")
    public String deleteDoctorById(String id) {
        if (doctorService.deleteById(id)) {
            return "Doctor with ID " + id + " deleted successfully";
        } else {
            return "Doctor with ID " + id + " not found";
        }
    }

    @ShellMethod(value = "Get total number of doctors.", key = "doctor count")
    public String getTotalNumberOfDoctors() {
        long totalDoctors = doctorService.getTotalNumber();
        return "Total number of doctors: " + totalDoctors;
    }

    @ShellMethod(value = "Display doctors by specialization.", key = "doctor show by specialization")
    public String showDoctorsBySpecialization(@ShellOption("specialization") String specialization) {
        List<Doctor> doctors = doctorService.findBySpecialization(specialization);
        if (doctors.isEmpty()) {
            return "No doctors found with specialization: " + specialization;
        }

        return createDoctorTable(doctors);
    }
}
