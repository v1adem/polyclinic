package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public void add(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
    }

    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord findById(long id) {
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findById(id);
        return medicalRecordOptional.orElse(null);
    }

    public boolean deleteById(long id) {
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordRepository.findById(id);
        if (medicalRecordOptional.isPresent()) {
            medicalRecordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long getTotalNumber() {
        return medicalRecordRepository.count();
    }
}
