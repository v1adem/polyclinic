package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
}
