package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public void add(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient findById(String id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.orElse(null);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll().stream().toList();
    }

    public boolean deleteById(String id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long getTotalNumber() {
        return patientRepository.count();
    }
}
