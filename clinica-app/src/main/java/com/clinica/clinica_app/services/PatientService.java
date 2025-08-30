package com.clinica.clinica_app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.clinica.clinica_app.models.Patient;

@Service
public class PatientService {
    
    private List<Patient> patients = new ArrayList<>();
    private Long nextId = 1L;

    public Patient createPatient(String firstName, String lastName, String documentId, 
                                LocalDate birthDate, String gender, String address, 
                                String phoneNumber, String emergencyContact, 
                                String allergies, String medicalConditions) {
        
        Patient patient = new Patient();
        patient.setId(nextId++);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDocumentId(documentId);
        patient.setBirthDate(birthDate);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);
        patient.setEmergencyContact(emergencyContact);
        patient.setAllergies(allergies);
        patient.setMedicalConditions(medicalConditions);
        
        patients.add(patient);
        return patient;
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patients.stream()
                                         .filter(p -> p.getId().equals(id))
                                         .findFirst();
        return patient.orElse(null);
    }

    public Patient getPatientByDocumentId(String documentId) {
        return patients.stream()
                      .filter(p -> p.getDocumentId().equals(documentId))
                      .findFirst()
                      .orElse(null);
    }

    public List<Patient> searchPatients(String searchTerm) {
        return patients.stream()
                      .filter(p -> p.getFirstName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                  p.getLastName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                  p.getDocumentId().contains(searchTerm))
                      .toList();
    }

    
}