package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public void add(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor findById(long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        return doctorOptional.orElse(null);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll().stream().toList();
    }

    public boolean deleteById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent()) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long getTotalNumber() {
        return doctorRepository.count();
    }

    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepository.findAll().stream()
                .filter(doctor -> doctor.getSpecialization().equals(specialization)).toList();
    }
}
