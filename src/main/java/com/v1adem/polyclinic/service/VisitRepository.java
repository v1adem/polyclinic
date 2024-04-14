package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>{
}
