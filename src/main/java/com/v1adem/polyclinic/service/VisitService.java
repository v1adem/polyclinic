package com.v1adem.polyclinic.service;

import com.v1adem.polyclinic.entity.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public void addVisit(Visit visit) {
        visitRepository.save(visit);
    }

    public Visit findById(long id) {
        Optional<Visit> patientOptional = visitRepository.findById(id);
        return patientOptional.orElse(null);
    }

    public List<Visit> findAll() {
        return visitRepository.findAll().stream().toList();
    }

    public boolean deleteVisitById(Long id) {
        Optional<Visit> patientOptional = visitRepository.findById(id);
        if (patientOptional.isPresent()) {
            visitRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long getTotalNumberOfVisits() {
        return visitRepository.count();
    }

    public void update(Visit visit) {
        visitRepository.save(visit);
    }
}
