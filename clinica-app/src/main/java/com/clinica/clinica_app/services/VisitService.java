package com.clinica.clinica_app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.clinica_app.models.Patient;
import com.clinica.clinica_app.models.User;
import com.clinica.clinica_app.models.Visit;

@Service
public class VisitService {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private UserService userService;
    
    private List<Visit> visits = new ArrayList<>();
    private Long nextId = 1L;

    public Visit registerVisit(Long patientId, Long nurseId, String bloodPressure, 
                              Double temperature, Integer heartRate, Integer respiratoryRate, 
                              Double oxygenSaturation, String medications, String procedures, 
                              String observations) {
        Patient patient = patientService.getPatientById(patientId);
        User nurse = userService.getUserById(nurseId);
        
        if (patient == null || nurse == null) {
            return null;
        }
        
        Visit visit = new Visit();
        visit.setId(nextId++);
        visit.setPatient(patient);
        visit.setNurse(nurse);
        visit.setBloodPressure(bloodPressure);
        visit.setTemperature(temperature);
        visit.setHeartRate(heartRate);
        visit.setRespiratoryRate(respiratoryRate);
        visit.setOxygenSaturation(oxygenSaturation);
        visit.setMedications(medications);
        visit.setProcedures(procedures);
        visit.setObservations(observations);
        
        visits.add(visit);
        return visit;
    }

    public List<Visit> getAllVisits() {
        return new ArrayList<>(visits);
    }

    public Visit getVisitById(Long id) {
        Optional<Visit> visit = visits.stream()
                                   .filter(v -> v.getId().equals(id))
                                   .findFirst();
        return visit.orElse(null);
    }

    public List<Visit> getVisitsByPatientId(Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            return visits.stream()
                       .filter(v -> v.getPatient().getId().equals(patientId))
                       .toList();
        }
        return List.of();
    }

    public List<Visit> getVisitsByNurseId(Long nurseId) {
        User nurse = userService.getUserById(nurseId);
        if (nurse != null) {
            return visits.stream()
                       .filter(v -> v.getNurse().getId().equals(nurseId))
                       .toList();
        }
        return List.of();
    }
}