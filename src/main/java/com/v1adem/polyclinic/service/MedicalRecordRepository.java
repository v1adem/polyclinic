package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
}
