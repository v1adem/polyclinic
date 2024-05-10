package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
}
