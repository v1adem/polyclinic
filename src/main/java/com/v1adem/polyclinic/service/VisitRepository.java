package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends MongoRepository<Visit, String> {

    List<Visit> findAllByPatientId(String id);
}
