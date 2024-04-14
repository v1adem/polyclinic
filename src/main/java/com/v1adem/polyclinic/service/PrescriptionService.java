package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Prescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public void add(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public Prescription findById(long id) {
        Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        return prescriptionOptional.orElse(null);
    }

    public boolean deleteById(long id) {
        Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        if (prescriptionOptional.isPresent()) {
            prescriptionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long getTotalNumber() {
        return prescriptionRepository.count();
    }
}
